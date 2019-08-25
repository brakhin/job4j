create table if not exists hall (
   id serial PRIMARY KEY,
   line integer not null,
   col integer not null,
   id_account integer default -1,
   UNIQUE(line, col)
);
