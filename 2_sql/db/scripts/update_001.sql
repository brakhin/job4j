create table vacancy(
	id serial primary key,
	name varchar(1000) not null unique,
	text varchar(100000) not null,
	link varchar(10000) not null
);

