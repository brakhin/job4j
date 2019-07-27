
create table if not exists users (
   id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
   name varchar(16) not null unique
);

