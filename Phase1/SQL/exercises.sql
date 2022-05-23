--2.d)  Criar os mecanismos que permitam inserir, remover e atualizar informação de um cliente 
--      particular (CC, NIF, nome, morada, cliente que o referenciou);

-- Procedure that allows to add a private client.
create procedure insert_particular(nif varchar, nome varchar, morada varchar, telefone varchar, cc varchar,
                                   referencia varchar = null)
    LANGUAGE plpgsql
as
$$
begin
insert into cliente
values (nif, nome, morada, telefone, 'P', referencia);
insert into particulares
values (nif, cc);
if
(nif not in (select cliente.nif from cliente)) then
       		raise notice 'Cliente nao inserido';
end if;
end;
$$;

drop procedure if exists insert_particular(nif varchar, nome varchar, morada varchar, telefone varchar, cc varchar, referencia varchar);

-- Procedure that allows to update the information of private client that already exits.
create procedure update_particular(NIF_ varchar, new_nome varchar, new_morada varchar, new_telefone varchar,
                                   new_ativo bit)
    LANGUAGE plpgsql
as
$$
begin
        ASSERT
(Nif_ is not null), "Nif can't be null";
	    if
(new_nome is not null) then
update CLIENTE
set nome = new_nome
where NIF = NIF_;
end if;
	    if
(new_morada is not null) then
update CLIENTE
set morada = new_morada
where NIF = NIF_;
end if;
	    if
(new_telefone is not null) then
update CLIENTE
set telefone = new_telefone
where NIF = NIF_;
end if;
	    if
(new_ativo is not null) then
update CLIENTE
set ativo = new_ativo
where NIF = NIF_;
end if;
end;
$$;

drop procedure if exists update_particular(nif_ varchar, nome_ varchar, morada_ varchar, telefone_ varchar, ativo_ bit);

-- Procedure that allows to remove a private client.
create Procedure remove_particular(nif_ varchar)
    LANGUAGE plpgsql
as
$$
declare
ativoCurr bit;
begin
update cliente
set ativo = B'0'
where (NIF = nif_);
select cliente.ativo
from cliente
where (NIF = nif_) into ativoCurr;
if
(ativoCurr <> B'0') then
            raise notice 'Cliente nao removido';
end if;
end;
$$;

drop procedure if exists remove_particular(nif_ varchar);

---------------------------------------------------------------------------------------------------------------------------------
--2.e)  Criar uma função que devolve o total de alarmes para um determinado ano e veículo 
--      passados como parâmetros; caso a matrícula do veículo seja vazia deve devolver as contagens 
--      de alarmes para todos os veículos nesse ano;

-- Procedure that gets the total number of allarms given an known vehicle and year.
create function number_of_alarms(ano integer, matricula_ varchar = null)
    returns integer
    language plpgsql
as
$$
begin
		if
(matricula_ is not null) then
			return count(matricula) numero_alarmes from Alarmes join GPS on id_gps = GPS.id where((matricula = matricula_) and (extract(year from GPS.marca_temporal) = ano));
else
			return count(matricula) numero_alarmes from Alarmes join GPS on id_gps = GPS.id;
end if;
end;
$$;

drop function if exists number_of_alarms(ano integer, nif_ varchar);

---------------------------------------------------------------------------------------------------------------------------------
--2.f)  Criar um procedimento que será chamado de forma periódica e que processa todos os 
--      registos não processados até esse momento. Deve tratar todos os registos existentes na 
--      tabela de registos não processados, de forma a copiar os dados para a tabela de registos 
--      processados ou de registos inválidos e remover as entradas tratadas. Deve garantir que 
--      processa todas as entradas não processadas até esse momento.

--Procedure that processes all the valid registers into Registos processados or if invalid into
--Registos invalidos
create procedure process_registers()
    language plpgsql
as
$$
declare
id_GPS_     integer;
    id_
integer;
begin
select id_gps
from Registos_nao_processados into id_gps_;
for id_gps_ in
select id_gps
from Registos_nao_processados loop
select id
from Registos_nao_processados
where (id_gps = id_gps_) into id_;
if
(id_gps_ in (select id from Gps)) then
            	insert into registos_processados values (id_, id_gps_);
else insert into registos_invalidos values (id_, id_gps_);
end if;
delete
from registos_nao_processados
where (id_gps = id_gps_);
end loop;
end;
$$;

drop procedure if exists process_registers();

---------------------------------------------------------------------------------------------------------------------------------
--2.g)  Criar um gatilho que permita analisar o registo processado, aquando da sua criação. e que 
--      gere o respetivo alarme caso esteja fora de qualquer uma das suas zonas verdes. Se não 
--      existirem zonas verdes ou se o equipamento estiver em pausa de alarmes não gera alarme.
--      Para a realização do gatilho, deverá usar a função zonaVerdeValida que recebe as 
--      coordenadas e o raio de uma zona verde e as coordenadas do registo em processamento e 
--      retorne true se as coordenadas do registo a ser processado se encontrarem dentro da zona 
--      verde e false caso contrário. Para teste implemente uma versão da função zonaVerdeValida 
--      que retorna true quando a o arredondamento da coordenada da latitude do registo for par e 
--      false quando for impar;

create
or replace function valid_green_zone(latitude_zv integer, longitude_zv integer, raio_zv integer, latitude_rp integer, longitude_rp integer)
    returns boolean
    language plpgsql
as 
$$
 	declare
estado_gps	varchar;
begin
select gps.estado
from gps
         join registos_processados on (gps.id = id_gps)
where ((latitude_rp = gps.latitude) and (longitude_rp = gps.longitude)) into estado_gps;
if
(estado_gps != 'PausaDeAlarmes') then
		    if(((latitude_zv + raio_zv) >= latitude_rp) and ((latitude_zv - raio_zv) <= latitude_rp) and ((longitude_zv + raio_zv) >= longitude_rp) and ((longitude_zv - raio_zv) <= longitude_rp )) then
			    return true;
else return false;
end if;
else return false;
end if;
end;
$$;

create
or replace function alarm_generator()
    returns trigger
    language plpgsql
as
$$
	declare
latitude_zv integer;
	 		longitude_zv
integer;
	 		raio_zv
integer;
			latitude_rp
integer;
			longitude_rp
integer;
begin
select gps.latitude
from gps
         join registos_processados on (gps.id = Registos_processados.id_gps)
where (new.id_gps = gps.id) into latitude_rp;
select gps.longitude
from gps
         join registos_processados on (gps.id = Registos_processados.id_gps)
where (new.id_gps = gps.id) into longitude_rp;
select raio
from Zona_verde
         join Veiculo on (Zona_verde.matricula = Veiculo.matricula)
         join GPS on (GPS.matricula = Veiculo.matricula)
         join Registos_processados on (Registos_processados.id_gps = Gps.id)
where (new.id_gps = GPS.id) into raio_zv;
select Zona_verde.longitude
from Zona_verde
         join Veiculo on (Zona_verde.matricula = Veiculo.matricula)
         join GPS on (GPS.matricula = Veiculo.matricula)
         join Registos_processados on (Registos_processados.id_gps = Gps.id)
where (new.id_gps = GPS.id) into longitude_zv;
select Zona_verde.latitude
from Zona_verde
         join Veiculo on (Zona_verde.matricula = Veiculo.matricula)
         join GPS on (GPS.matricula = Veiculo.matricula)
         join Registos_processados on (Registos_processados.id_gps = Gps.id)
where (new.id_gps = GPS.id) into latitude_zv;
if
(
select valid_green_zone(latitude_zv, longitude_zv, raio_zv, latitude_rp, longitude_rp)) then
insert
into alarmes
values (new.id, new.id_gps);
end if;
return new;
end;
$$;

create
or replace trigger vgz
after insert on Registos_processados
for each row
execute function alarm_generator();

drop trigger if exists vgz on Registos_processados;
drop function alarm_generator() cascade;

---------------------------------------------------------------------------------------------------------------------------------
-- 2.h) Desenvolver um procedimento que crie um veículo com a respectiva informação do 
--      equipamento associado, e que o associe a um cliente. Caso sejam passados dados para a 
--      criação de uma zona verde, deve criar e associar o veículo a essa zona. Reutilize 
--      procedimentos já existentes ou crie novos se necessário; Deve garantir as restrições de 
--      negócio respectivas, nomeadamente a limitação do número de veículos.

create procedure add_veicule_to_green_zone(green_zone_id integer, zone_radius integer, gps_lat integer, gps_lon integer,
                                           car_matricula varchar)
    language plpgsql
as
$$
begin
insert into Zona_Verde
values (green_zone_id, zone_radius, gps_lon, gps_lat, car_matricula);
if
(green_zone_id not in (select zona_verde.id from zona_verde where (zona_verde.matricula = car_matricula))) then
            raise notice 'Veiculo nao associado a Zona Verde';
end if;
end;
$$;

create procedure add_vehicle_to_client_or_not(matricula varchar, driver varchar, phone_driver varchar,
                                              client_nif varchar, green_zone_id integer, zone_radius integer,
                                              zone_gps_lat integer, zone_gps_lon integer)
    language plpgsql
as
$$
declare
car_client_count varchar := 0;
begin
        if
(client_nif in (select cliente.nif FROM cliente)) then
			insert into Veiculo values(matricula, driver, phone_driver, client_nif, null);
call add_veicule_to_green_zone(green_zone_id, zone_radius, zone_gps_lat, zone_gps_lon, matricula);
else raise notice 'Client not found';
end if;
        if
(matricula not in (select veiculo.matricula from veiculo)) then
            raise notice 'Veiculo nao inserido';
end if;
end;
$$;

drop procedure if exists add_veicule_to_green_zone(green_zone_id integer, zone_radius integer, gps_lat integer, gps_lon integer, matricula varchar);
drop procedure if exists add_vehicle_to_client_or_not(matricula varchar, driver varchar, phone_driver varchar, client_nif varchar, green_zone_id integer, zone_radius integer, zone_gps_lat integer, zone_gps_lon integer)

---------------------------------------------------------------------------------------------------------------------------------
--2.i)  Criar uma vista, que liste todos os alarmes. A vista deve apresentar a matrícula do veículo, o 
--      nome do condutor, as coordenadas e a data/hora do alarme;

create view all_alarms as
select Alarmes.id             as id_alarm,
       Veiculo.matricula,
       nome_condutor,
       latitude,
       longitude,
       Alarmes.marca_temporal as dia_hora
from Veiculo
         join GPS
              on (Veiculo.matricula = GPS.matricula)
         join Alarmes
              on (Alarmes.id_GPS = GPS.id);

drop view if exists all_alarms;

---------------------------------------------------------------------------------------------------------------------------------
--2.j)  Adicione suporte de modo que a execução da instrução INSERT sobre a vista da alínea 
--      anterior permita adicionar um alarme e o respectivo registo tratado;

create
or replace function add_alarm()
    returns trigger
    language plpgsql
as
$$
    declare
nome_do_condutor    varchar;
        iden_gps
integer;
begin
select nome_condutor
from Veiculo
where (matricula = new.matricula) into nome_do_condutor;
select id
from gps
where (matricula = new.matricula) into iden_gps;
if
((new.matricula in (select matricula from Veiculo)) and new.nome_condutor = nome_do_condutor) then
            insert into registos_processados values (new.id_Alarme, iden_gps);--temos que fazer isto com serial!!
update GPS
set latitude = new.latitude
where (gps.id = iden_gps);
update GPS
set longitude = new.longitude
where (gps.id = iden_gps);
return new;
else
            raise notice 'Veiculo inexistente ou Nome do condutor errado';
return old;
end if;
end;
$$;

create
or replace trigger adding_alarm
    instead of insert on all_alarms
    for each row
    execute function add_alarm();

drop trigger if exists adding_alarm on all_alarms;
drop function add_alarm() cascade;

---------------------------------------------------------------------------------------------------------------------------------
--2.k)  Implemente o procedimento que será chamado diariamente e que apaga os registos 
--      inválidos existentes com duração superior a 15 dias;

create procedure eliminate_invalid_registers()
    language plpgsql
as
$$
begin
delete
from Registos_Invalidos
where (marca_temporal + interval '15' day <= current_timestamp);
end;
$$;

drop procedure if exists eliminate_invalid_registers();

---------------------------------------------------------------------------------------------------------------------------------
--2.l)  Crie os mecanismos necessários para que a execução da instrução DELETE sobre a tabela de 
--      cliente permita desativar o(s) Cliente(s) sem apagar os seus dados. Assuma que podem ser 
--      apagados vários clientes em simultâneo;

create
or replace function dst_clt()
	returns trigger 
	language plpgsql
as 
$$
begin
update Cliente
set ativo = B'0'
where (nif = old.nif);
return new;
end;
$$;

create
or replace trigger desactivate_client
	before delete
on Cliente
	for each row
	execute function dst_clt();

drop trigger if exists desactivate_client on Cliente;
drop function dst_clt() cascade;

---------------------------------------------------------------------------------------------------------------------------------
--2.m)  Crie os mecanismos necessários para que, de forma automática, quando é criado um 
--      alarme, o número de alarmes do veículo seja actualizado;

create
or replace function inc_alrm()
	returns trigger 
	language plpgsql
as 
$$
begin
update veiculo
set n_alarmes = n_alarmes + 1
where (veiculo.matricula in
       (select gps.matricula
        from gps
                 join alarmes on gps.id = alarmes.id_gps
        where (gps.id = new.id_gps)));
return null;
end;
$$;

create
or replace trigger increase_alarms
after insert on alarmes
for each row
execute procedure inc_alrm();

drop trigger if exists increase_alarms on Registos_processados cascade;
drop function if exists inc_alrm() cascade;
