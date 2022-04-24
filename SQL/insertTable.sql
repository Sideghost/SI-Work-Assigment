insert into Cliente values	(123456780, 'joca', 'casa do joca', 123456780, 'P', B'1', null),
							(123456781, 'esdrubal', 'casa do esdrubal', 123456781, 'P', B'1', null),
							(123456782, 'gertrudes', 'casa da gertrudes', 123456782, 'P', B'1', null),
							(123456783, 'josefina', 'casa da josefina', 123456783, 'P', B'1', null),
							(123456784, 'joseuina', 'casa do joseuina', 123456784, 'P', B'1', null);

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

insert into Veiculo values 	('12345A', 'agnaldo', 123456789, 123456780, 0),
							('AE12ER', 'antonio', 123456789, 123456782, 0),
							('AE15RQ', 'fernando', 123456789, 123456781, 0),
							('AA16AA', 'fernanda', 123456789, 123456783, 0),
							('AB18PE', 'poliwrath', 123456789, 123456784, 0);

insert into Zona_verde values	(1, 500, 10, 'AE12ER'),
								(2, 500, 10, 'AE12ER'),
								(3, 500, 10, 'AE15RQ'),
								(4, 500, 10, '12345A'),
								(5, 500, 10, 'AB18PE');
						
insert into GPS values	(1, 1, 10, 'AE12ER'),
						(2, 50, 10, 'AE12ER'),
						(3, 50, 15, '12345A'),
						(4, 10, 15, 'AB18PE'),
						(5, 8, 10, 'AA16AA');
					
--insert into Registos_Nao_Processados 
