--1.d)

create procedure insert_particular(nif varchar, nome varchar, morada varchar, telefone varchar, referencia varchar, cc varchar)    
    LANGUAGE plpgsql
as
$$
    begin    
        insert into cliente values (nif,nome,morada,telefone,'P',referencia);
        insert into particulares values (nif,cc);
        if(nif not in (select cliente.nif from cliente)) then
       		raise notice 'Cliente n�o inserido';        
    	end if;
    end;
$$;
    
drop procedure if exists insert_particular(nif varchar, nome varchar, morada varchar, telefone varchar, referencia varchar, cc varchar); 

call insert_particular('123456791', 'FCPORCO', 'Rua abcd', '967995934', null, '15111660'); 
------------------------------------------------------------------------------------------------------------------------
create procedure update_particular(NIF_ varchar, new_nome varchar, new_morada varchar, new_telefone varchar, new_ativo bit)--ativo poderia estar como um varchar
    LANGUAGE plpgsql
as
$$
    begin
	   if (new_nome is not null) then 
	   update CLIENTE set nome = new_nome where NIF = NIF_; 
	   end if;
	   if (new_morada is not null) then 
	   update CLIENTE set morada = new_morada where NIF = NIF_;
	   end if; 
	   if (new_telefone is not null) then 
	   update CLIENTE set telefone = new_telefone where NIF = NIF_;
	   end if; 
	   if (new_ativo is not null) then 
	   update CLIENTE set ativo = new_ativo where NIF = NIF_;
   	   end if;
	   end;
$$;

drop procedure if exists update_particular(nif_ varchar,nome_ varchar,morada_ varchar, telefone_ varchar, ativo_ bit);

call update_particular('123456791' ,'DJDODIA' , null , null, B'0');
------------------------------------------------------------------------------------------------------------------------
create Procedure remove_particular(nif_ varchar)
    LANGUAGE plpgsql
as
$$
declare 
        ativoCurr bit;
    begin    
        update cliente set ativo = B'0' where NIF = nif_;
        select cliente.ativo  from cliente where (NIF = nif_) into ativoCurr;
        if(ativoCurr <> B'0') then
            raise notice 'Cliente n�o removido';
        end if;
        end;
$$;

drop procedure if exists remove_particular(nif_ varchar);

call remove_particular('123456780'); 

--1.e)

create function number_of_alarms(ano integer,matricula_ varchar = null)
	returns integer
	language plpgsql
as
$$
	begin
		if (matricula_ is not null) then
			return count(matricula) numero_alarmes from Alarmes join GPS on id_gps = GPS.id where (matricula = matricula_ and extract(year from GPS.marca_temporal) = ano);
		else
			return count(matricula) numero_alarmes from Alarmes join GPS on id_gps = GPS.id;
		end if;
	end;
$$

drop function if exists number_of_alarms(ano integer, nif_ varchar);

select number_of_alarms(2022, 'AE12ER')

--1.f)

create procedure process_registers()
	language plpgsql
as
$$
declare
		id_zona_ integer;
		id_GPS_	 integer;
	begin 
		select id_zona from Registos_Nao_Processados 
 	end;
$$

call process_registers();

drop procedure process_registers()
		
--h)

create procedure add_vehicle_to_client_or_not(matricula varchar, driver varchar, phone_driver varchar, client_nif varchar, green_zone_id integer, zone_radius integer, zone_gps_coords integer)
    language plpgsql
as
$$
    begin 
        if (client_nif in (select cliente.nif FROM cliente)) then
            if(not (client_nif in(select cliente.tipo = 'P'))) then
                insert into Veiculo values(matricula, driver, phone_driver, client_nif, null);
                    insert into Zona_Verde values(green_zone_id, zone_radius, zone_gps_coords, matricula);
            else 
                if(count(client.nif = Veiculo.nif) < 3) then
                    insert into Veiculo values(matricula, driver, phone_driver, client_nif, 0);
                    if ((green_zone_id is not null) and (zone_radius is not null) and (zone_gps_coords is not null)) then
                        insert into Zona_Verde values (green_zone_id, zone_radius, zone_gps_coords, matricula);
                    end if;
                end if;
            end if;
        end if;
        if (matricula not in (select veiculo.matricula from veiculo)) then 
            raise notice 'Veiculo n�o inserido';
        end if;
    end;
$$;

drop procedure if exists add_vehicle_to_client_or_not(matricula varchar, driver varchar, phone_driver varchar, client_nif varchar, green_zone_id integer, zone_radius integer, zone_gps_coords integer)

call add_vehicle_to_client_or_not('BBBBBB','Josevaldo das caricas','967995999','123456779',6,500,10) 

--i)

create view all alarms as
	select Veiculo.matricula, nome_condutor, latitude, longitude, Alarmes.marca_temporal as dia_hora 
		from Veiculo 
			join GPS
			on (Veiculo.matricula = GPS.matricula)
			join Alarmes
			on (Alarmes.id_GPS = GPS.id)
			group by Veiculo.matricula;
			
drop view if exists alarms;

--k)
	
create procedure eliminate_invalid_registers() 
	language plpgsql
as
$$
	begin 
		delete
		from Registos_Invalidos
		where(marca_temporal + interval '15' day <= current_timestamp);
	end;
$$;
drop procedure if exists eliminate_invalid_registers();

call eliminate_invalid_registers()

--l)

create or replace function dst_clt()
	returns trigger 
	language plpgsql
as 
$$
	begin
		update Cliente set ativo = B'0'
		where (nif = old.nif);
		return new;
	end;
$$;
	


create or replace trigger desactivate_client
	before delete on Cliente
	for each row
	execute function dst_clt();
	----------------------------------------------------------------------------
	
--m)

--create or replace function inc_alrm() 
	--returns trigger 
	--language plpgsql
--as 
--$$
	--begin
		--update veiculo 
		--set n_alarmes = n_alarmes + 1		
		--where veiculo.matricula in 
		--(select gps.matricula
		--from gps join alarmes on gps.id = alarmes.id_gps 
		--);
		--return null;
	--end;
--$$;
	
	
									
create or replace trigger increase_alarms
after insert on alarmes
for each row
execute procedure inc_alrm();

--drop trigger if exists increase_alarms on Registos_processados cascade;

--drop function if exists inc_alrm() cascade;