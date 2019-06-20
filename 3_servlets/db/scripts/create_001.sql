
create table if not exists users (
   id serial PRIMARY KEY,
   login varchar(16) not null unique,
   password varchar(16) not null,
   city integer not null default 1,
   role integer not null default 1
);

