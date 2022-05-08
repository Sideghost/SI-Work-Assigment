

begin transaction;
set ISOLATION LEVEL READ COMMITTED;


--1.d)
declare dummy_D_NIF VARCHAR := '123456791';
declare dummy_D_name VARCHAR := 'FCPORTO';
declare dummy_D_address VARCHAR := 'Rua abcd';
declare dummy_D_phone VARCHAR := '967995934';
declare dummy_D_ref VARCHAR := null;
declare dummy_D_cc VARCHAR := '15111660';

declare dummy_D_new_name VARCHAR := 'DJDODIA';
declare dummy_D_new_address VARCHAR := null;
declare dummy_D_new_phone := null;
declare dummy_D_new_state := B'0';

call insert_particular(dummy_D_NIF, dummy_D_name, dummy_D_address, dummy_D_phone, dummy_D_ref, dummy_D_cc);

call update_particular(dummy_D_NIF ,dummy_D_new_name , dummy_D_new_address , dummy_D_new_phone, dummy_D_new_state);

call remove_particular(dummy_D_NIF);

--1.e)

declare dummy_E_year INTEGER := 2022;
declare dummy_E__licence_plate varchar := 'AE12ER';

select number_of_alarms(dummy_E_year, dummy_E__licence_plate);

--1.f)
drop procedure process_registers();

--1.g)

--1.h)
declare dummy_H_licence_plate varchar := 'BBBBBB';
declare dummy_H_driver varchar := 'Josevaldo das caricas';
declare dummy_H_phone_driver varchar := '967995999';
declare dummy_H_nif varchar := '123456779';
declare dummy_H_green_zone_id integer := 6;
declare dummy_H_zone_radius integer := 500;
declare dummy_H_zone_gps_coords integer := 10;

call add_vehicle_to_client_or_not(dummy_H_licence_plate, dummy_H_driver,dummy_H_phone_driver,dummy_H_nif,dummy_H_green_zone_id,dummy_H_zone_radius,dummy_H_zone_gps_coords);

--1.i)
drop view if exists alarmes;

--1.j)

--1.k)
call eliminate_invalid_registers();

--1.l)

--1.m)


ROLLBACK;