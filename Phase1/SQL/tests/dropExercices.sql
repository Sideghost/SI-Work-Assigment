--2.d)

drop procedure if exists insert_particular(nif varchar, nome varchar, morada varchar, telefone varchar, referencia varchar, cc varchar);

drop procedure if exists update_particular(nif_ varchar, nome_ varchar, morada_ varchar, telefone_ varchar, ativo_ bit);

drop procedure if exists remove_particular(nif_ varchar);

--2.e)

drop function if exists number_of_alarms(ano integer, nif_ varchar);

--2.f)

drop procedure if exists process_registers();

--2.g)

drop trigger if exists vgz on Registos_processados;
drop function alarm_generator() cascade;

--2.h)

drop procedure if exists add_veicule_to_green_zone(green_zone_id integer, zone_radius integer, gps_lat integer, gps_lon integer, matricula varchar);
drop procedure if exists add_vehicle_to_client_or_not(matricula varchar, driver varchar, phone_driver varchar, client_nif varchar, green_zone_id integer, zone_radius integer, zone_gps_lat integer, zone_gps_lon integer)

--2.i)

drop view if exists all_alarms;

--2.j)

drop trigger if exists adding_alarm on all_alarms;
drop function add_alarm() cascade;

--2.k)

drop procedure if exists eliminate_invalid_registers();

--2.l)

drop trigger if exists desactivate_client on Cliente;
drop function dst_clt() cascade;

--2.m)

drop trigger if exists increase_alarms on Registos_processados cascade;
drop function if exists inc_alrm() cascade;
