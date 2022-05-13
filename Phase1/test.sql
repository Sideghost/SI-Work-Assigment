begin transaction;
set ISOLATION LEVEL serializable ;

--1.d)
 --dummy_D_NIF VARCHAR := '123456791';
 --dummy_D_name VARCHAR := 'FCPORTO';
 --dummy_D_address VARCHAR := 'Rua abcd';
 --dummy_D_phone VARCHAR := '967995934';
 --dummy_D_ref VARCHAR := null;
 --dummy_D_cc VARCHAR := '15111660';

 --dummy_D_new_name VARCHAR := 'DJDODIA';
 --dummy_D_new_address VARCHAR := null;
 --dummy_D_new_phone := null;
 --dummy_D_new_state := B'0';



call insert_particular('1234567891','FCPORTO' , 'Rua abcd' , '967995934', null, '15111660');

call update_particular('1234567891' ,'SLBENFICA', 'Rua dcba', '967777777', 'Activo');

call remove_particular('1234567891');

--1.e)

--declare dummy_E_year INTEGER := 2022;
--declare dummy_E__licence_plate varchar := 'AE12ER';

select number_of_alarms(2022, 'AE12ER');

--1.f)

insert into registos_nao_processados values (12, 30);

call process_registers();

--1.g)

insert into registos_processados values (19, 1);

--1.h)
--declare dummy_H_licence_plate varchar := 'BBBBBB';
--declare dummy_H_driver varchar := 'Josevaldo das caricas';
--declare dummy_H_phone_driver varchar := '967995999';
--declare dummy_H_nif varchar := '123456779';
--declare dummy_H_green_zone_id integer := 6;
--declare dummy_H_zone_radius integer := 500;
--declare dummy_H_zone_gps_coords integer := 10;

call add_vehicle_to_client_or_not('BBBBBB', 'Josevaldo das caricas','967995999','123456779',6,500,10);

--1.i)
drop view if exists alarmes;

--1.j)

insert into all_alarms values (65, '12345A', 'agnaldo', 98, 98, '2022-04-07 16:39:53.171');

--1.k)

call eliminate_invalid_registers();

--1.l)

delete from Cliente;

--1.m)

insert into alarmes values(36,3);

ROLLBACK;