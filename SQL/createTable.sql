 create table Cliente(
 	NIF		 varchar(9)	 not null,
 	nome 	 varchar(30) not null,
 	morada	 varchar(30) not null,
 	telefone varchar(9)  not null,
 	tipo 	 varchar(1)	 not null,
 	ativo	 bit		 not null,
 	referencia	varchar(9),
 	primary key (NIF),
 	constraint tipo_errado check(tipo like 'I' or tipo like 'P'),
 	constraint telefone_errado check (telefone similar to '[0-9]{9}'),
 	constraint NIF_errado check(NIF similar to '[0-9]{9}'),
 	constraint referencia_errada check (referencia similar to '[0-9]{9}'),
 	constraint referencia_proprio check (referencia <> NIF)
);

create table Particulares(
 	NIF		varchar(9)		not null,
 	CC		varchar(14)		not null,
 	constraint NIF_errado check(NIF similar to '[0-9]{9}'),
 	constraint CC_errado check(CC similar to'[0-9]{8} [0-9] [A-Z]{2}[0-9]'),
 	primary key(NIF),
 	foreign key (NIF) references CLiente (NIF)
);

ALTER TABLE Cliente ADD FOREIGN KEY (referencia) REFERENCES Particulares(NIF) on delete cascade on update cascade;
 
 create table Institucionais(
 	NIF				varchar(9)	not null,
 	Nome_contacto	varchar(30)	not null,
 	constraint NIF_errado check(NIF similar to '[0-9]{9}'),
 	primary key(NIF),
 	foreign key (NIF) references Cliente (NIF)
);

create table Veiculo(
	matricula		  integer		not null,
	nome_condutor 	  varchar(30)	not null,
	telefone_condutor varchar(9),
	NIF				  varchar(9)	not null,
	n_alarmes		  integer,	  --not null 
	constraint telefone_errado check (telefone_condutor similar to '[0-9]{9}'),
	constraint NIF_errado check(NIF similar to '[0-9]{9}'),	
	primary key (matricula),
	foreign key(NIF) references Cliente (NIF)
);

create table Zona_Verde(
	matricula	integer	not null,
	raio		integer	not null,
	coordenadas_GPS		integer not null,
	primary key (matricula),
	foreign key (matricula) references Veiculo (matricula)
);

create table GPS(
	id 				integer	  not null,
	coordenadas		integer   not null,
	marca_temporal 	timestamp not null,
	matricula		integer	  not null,
	primary key (id),
	foreign key (matricula) references Veiculo(matricula)
);




