--1.d)

CREATE procedure insert_particular(nif varchar,nome varchar,morada varchar,telefone varchar,ativo bit,referencia varchar,cc varchar)    
    LANGUAGE plpgsql
as
$$
    BEGIN    
        INSERT INTO cliente values (nif,nome,morada,telefone,'P',ativo,referencia);
        INSERT INTO particulares VALUES (nif,cc);
        if(nif not in (SELECT cliente.nif FROM cliente)) then
       		raise notice 'Cliente n�o inserido';        
    	END IF;
    end;
$$;
    
drop procedure if exists insert_particular(nif varchar,nome varchar,morada varchar, telefone varchar,tipo varchar,ativo bit,referencia varchar,cc varchar); 

call insert_particular('123456799' ,'FCPORCO' ,'Rua abcd','967995934' ,B'1',null,'15111660'); 

CREATE procedure update_particular(NIF_ varchar, new_nome varchar, new_morada varchar, new_telefone varchar, new_ativo bit)--ativo poderia estar como um varchar
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

call update_particular('123456779' ,'DJDODIA' , null , null, B'1');

-- 1.h) --dica pardal(ito) -> usar cursor

create procedure add_veicule_to_green_zone(green_zone_id integer, zone_radius integer, zone_gps_coords integer, matricula varchar)
    language plpgsql
as
$$
    begin
        if ((green_zone_id is not null) and (zone_radius is not null) and (zone_gps_coords is not null)) then
            insert into Zona_Verde values(green_zone_id, zone_radius, zone_gps_coords, matricula);
        end if;
        if (green_zone_id not in (select zona_verde.green_zone_id from veiculo)) then 
            raise notice 'Veiculo nao associado a Zona Verde';
        end if;
        
    end;
$$;

create procedure add_vehicle_to_client_or_not(matricula varchar, driver varchar, phone_driver varchar, client_nif varchar, green_zone_id integer, zone_radius integer, zone_gps_coords integer)
    language plpgsql
as
$$
    begin 
        if (client_nif in (select cliente.nif FROM cliente)) then
            if(not (client_nif in(select cliente.tipo = 'P'))) then
                insert into Veiculo values(matricula, driver, phone_driver, client_nif, null);
                call add_veicule_to_green_zone(green_zone_id, zone_radius, zone_gps_coords, matricula);
            else 
                if(count(client.nif = Veiculo.nif) < 3) then
                    insert into Veiculo values(matricula, driver, phone_driver, client_nif, 0);
                    call add_veicule_to_green_zone(green_zone_id, zone_radius, zone_gps_coords, matricula);
                end if;
            end if;
        end if;
        if (matricula not in (select veiculo.matricula from veiculo)) then 
            raise notice 'Veiculo nao inserido';
        end if;
    end;
$$;

drop procedure if exists add_vehicle_to_client_or_not(matricula varchar, driver varchar, phone_driver varchar, client_nif varchar, green_zone_id integer, zone_radius integer, zone_gps_coords integer)

call add_vehicle_to_client_or_not('BBBBBB','Josevaldo das caricas',967995999,123456779,6,500,10) 
