create function max_number_of_cars()
    returns trigger
    language plpgsql
    as 
    $$
declare
tipo_cliente varchar(1);
            numero_veiculos
integer;
begin
select Cliente.tipo
from Cliente
         join Veiculo on (Veiculo.nif = Cliente.nif)
where (Cliente.nif = new.nif) into tipo_cliente;
select count(NIF) numero_veiculos
from Veiculo
where (Veiculo.nif = new.nif) into numero_veiculos;
for tipo_cliente in
select tipo
from Cliente loop if(tipo_cliente = 'P') then
                        if (numero_veiculos >= 3) then 
                            return old;
raise
notice 'Cliente nao pode ter mais veiculos associados';
else return new;
end if;
else return new;
end if;
end loop;
end;
    $$;

drop function if exists max_number_of_cars() cascade;

create trigger number_of_cars
    before insert
    on Veiculo
    for each row
    execute function max_number_of_cars();

drop trigger if exists number_of_cars on Veiculo;
