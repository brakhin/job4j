create table item (
	id serial primary key,
	name varchar(100) not null,
	description varchar(100) not null,
	created bigint not null,
	comments varchar(100)
);
