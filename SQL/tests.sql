

begin transaction;
set ISOLATION LEVEL READ COMMITTED;


--1.d)
call insert_particular('123456791', 'FCPORCO', 'Rua abcd', '967995934', null, '15111660');

call update_particular('123456791' ,'DJDODIA' , null , null, B'0');

call remove_particular('123456780'); 

--1.e)
select number_of_alarms(2022, 'AE12ER');

--1.f)
drop procedure process_registers();

--1.g)

--1.h)
call add_vehicle_to_client_or_not('BBBBBB','Josevaldo das caricas',967995999,123456779,6,500,10);

--1.i)
drop view if exists alarmes;

--1.j)

--1.k)
call eliminate_invalid_registers();

--1.l)

--1.m)

