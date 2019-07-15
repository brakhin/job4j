
create table if not exists users (
   id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
   login varchar(16) not null unique,
   password varchar(16) not null,
   role integer not null default 0
);

