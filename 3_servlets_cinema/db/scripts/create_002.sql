create table if not exists account (
   id serial PRIMARY KEY,
   username varchar(100) not null,
   phone varchar(100) not null
);
