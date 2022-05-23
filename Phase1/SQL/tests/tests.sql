begin
transaction;
set
ISOLATION LEVEL SERIALIZABLE;

--1.d)
declare
dummy_D_NIF VARCHAR := '123456791';
declare
dummy_D_name VARCHAR := 'FCPORTO';
declare
dummy_D_address VARCHAR := 'Rua abcd';
declare
dummy_D_phone VARCHAR := '967995934';
declare
dummy_D_ref VARCHAR := null;
declare
dummy_D_cc VARCHAR := '15111660';

declare
dummy_D_new_name VARCHAR := 'DJDODIA';
declare
dummy_D_new_address VARCHAR := null;
declare
dummy_D_new_phone := null;
declare
dummy_D_new_state := B'0';

call insert_particular(dummy_D_NIF, dummy_D_name, dummy_D_address, dummy_D_phone, dummy_D_ref, dummy_D_cc);

call update_particular(dummy_D_NIF ,dummy_D_new_name , dummy_D_new_address , dummy_D_new_phone, dummy_D_new_state);

call remove_particular(dummy_D_NIF);

--1.e)

declare
dummy_E_year INTEGER := 2022;
declare
dummy_E__licence_plate varchar := 'AE12ER';

select number_of_alarms(dummy_E_year, dummy_E__licence_plate);

--1.f)

insert into registos_nao_processados
values (12, 30);

call process_registers();

--1.g)

insert into registos_processados
values (19, 1);

--1.h)

--hope it works
call add_vehicle_to_client_or_not ('BBBBBB', 'Josevaldo das caricas', '967995999', '123456779', 6, 500, 10, 10);

-- tudo a null vai dar treta 
call add_veicule_to_client_or_not (null, null, null, null, null, null, null, null);

--client nif must be in db
call add_veicule_to_client_or_not ('ABABAB', 'maria', '123123123', '123456778', 6, 500, 10, 10);

--green zone must have values 
call add_veicule_to_client_or_not ('ABABAB', 'maria', '123123123', '123456778', null, null, null, null);

--last wont work
call add_vehicle_to_client_or_not ('BBBBBB', 'Josevaldo das caricas', '967995999', '123456779', 6, 500, 10, 10);
call add_vehicle_to_client_or_not ('BBBBBB', 'Josevaldo das caricas', '967995999', '123456779', 6, 500, 10, 10);
call add_vehicle_to_client_or_not ('BBBBBB', 'Josevaldo das caricas', '967995999', '123456779', 6, 500, 10, 10);


--1.i)
drop view if exists alarmes;

--1.j)

insert into all_alarms
values (65, '12345A', 'agnaldo', 98, 98, '2022-04-07 16:39:53.171');

--1.k)

call eliminate_invalid_registers();

--1.l)

delete
from Cliente;

--1.m)

insert into alarmes
values (36, 3);

ROLLBACK;