
-- Client values 
insert into Cliente values	(123456780, 'joca', 'casa do joca', 123456780, 'P',null),
							(123456781, 'esdrubal', 'casa do esdrubal', 123456781, 'P',null),
							(123456782, 'gertrudes', 'casa da gertrudes', 123456782, 'P',null),
							(123456783, 'josefina', 'casa da josefina', 123456783, 'P',null),
							(123456784, 'joseuina', 'casa do joseuina', 123456784, 'P',null);

insert into Particulares values (123456783, '12345678'),
								(123456782, '12345678'),
								(123456781, '12345678'),
								(123456784, '12345678'),
								(123456780, '12345678');

update Cliente set referencia = 123456780 where nif = '123456781';
update Cliente set referencia = 123456780 where nif = '123456782';

insert into Institucionais values 	(123456783, '12345678'),
									(123456782, '12345678'),
									(123456781, '12345678'),
									(123456784, '12345678'),
									(123456780, '12345678');

-- Vehicle values
insert into Veiculo values 	('12345A', 'agnaldo', 123456789, 123456780, 0),
							('AE12ER', 'antonio', 123456789, 123456782, 0),
							('AE15RQ', 'fernando', 123456789, 123456781, 0),
							('AA16AA', 'fernanda', 123456789, 123456783, 0),
							('AB18PE', 'poliwrath', 123456789, 123456784, 0),
							('12345E', 'slb', 123456780, 123456780, 0),
							('12344E', 'slb', 123456780, 123456780, 0),
							('12333A', 'cagativo', 123456780, 123456780, 0);

-- Gps values
insert into GPS values	(1, 1, 10,'AE12ER', 'Activo'),
						(2, 50, 10,'AE12ER', 'Activo'),
						(3, 50, 15, '12345A', 'Activo'),
						(4, 10, 15, 'AB18PE', 'Activo'),
						(5, 8, 10, 'AA16AA', 'PausaDeAlarmes');

-- Green Zone values
insert into Zona_verde values	(1, 500, 10, 10, 'AE12ER'),
								(2, 500, 20, 20,'AE12ER'),
								(3, 500, 10, 30,'AE15RQ'),
								(4, 500, 5, 40,'12345A'),
								(5, 500, 20, 50,'AB18PE');