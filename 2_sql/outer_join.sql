create table carbody (
id serial primary key,
name varchar(100)
);

create table engine (
id serial primary key,
name varchar(100)
);

create table transmission (
id serial primary key,
name varchar(100)
);


create table car(
id serial primary key,
id_carbody int reference carbody(id),
id_engine int reference engine(id),
id_transmission int reference transmission(id)
);

insert into carbody(name) values('carbody_1');
insert into carbody(name) values('carbody_2');
insert into carbody(name) values('carbody_3');

insert into engine(name) values('engine_1');
insert into engine(name) values('engine_2');
insert into engine(name) values('engine_3');

insert into transmission(name) values('transmission_1');
insert into transmission(name) values('transmission_2');
insert into transmission(name) values('transmission_3');

insert into car(name, id_carbody, id_engine, id_transmission) values ('car_1', 1, 1, 2);
insert into car(name, id_carbody, id_engine, id_transmission) values ('car_2', 1, 3, 1);
insert into car(name, id_carbody, id_engine, id_transmission) values ('car_3', 3, 1, 1);

select b.* from carbody b, car c on b.id = car.id_carbody where b.id is null;

select e.* from engine e, car c on e.id = car.id_engine where c.id is null;

select t.* from transmission t, car c on t.id = car.id_transmission where t.id is null;

