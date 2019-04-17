-- СозданиеБД
create database uml_demo;

-- Создание таблиц
create table role (
    id serial primary key,
    role_name varchar(20)
);

create table category (
    id serial primary key,
    category_name varchar(20)
);

create table state (
    id serial primary key,
    state_name varchar(20)
);

create table item (
    id serial primary key,
    item_name varchar(20),
    id_category integer references category(id),
    id_state integer references state(id)
);




create table user1 (
    id serial primary key,
    user_name varchar(20),
    id_role integer references role(id),
    id_item integer unique references item(id)
);


create table rules (
    id serial primary key,
    rule_name varchar(20)
);

create table role_rules (
    id serial primary key,
    id_role integer references role(id),
    id_rule integer references rules(id)
);


create table attachs (
    id serial primary key,
    attach_name varchar(20),
    id_item integer references item(id)
);



create table comments (
    id serial primary key,
    comment_name varchar(20),
    id_item integer references item(id)
);

-- Заполнение данными

insert into category(category_name) values('category_name_1');
insert into category(category_name) values('category_name_2');
insert into category(category_name) values('category_name_3');

insert into comments(comment_name) values('comment_name_1');
insert into comments(comment_name) values('comment_name_2');
insert into comments(comment_name) values('comment_name_3');

insert into state(state_name) values('state_name_1');
insert into state(state_name) values('state_name_2');
insert into state(state_name) values('state_name_3');

insert into item(item_name, id_category, id_state) values('item_name_1', 1, 1);
insert into item(item_name, id_category, id_state) values('item_name_2', 2, 1);
insert into item(item_name, id_category, id_state) values('item_name_4', 1, 2);
insert into item(item_name, id_category, id_state) values('item_name_4', 2, 3);

insert into comments(comment_name, id_item) values('comment_name_1', 1);
insert into comments(comment_name, id_item) values('comment_name_2', 2);

insert into attachs(attach_name, id_item) values('attach_name_1', 1);
insert into attachs(attach_name, id_item) values('attach_name_2', 2);


insert into role(role_name) values('role_name_1');
insert into role(role_name) values('role_name_2');
insert into role(role_name) values('role_name_3');

insert into rules(rule_name) values('rule_name_1');
insert into rules(rule_name) values('rule_name_2');
insert into rules(rule_name) values('rule_name_3');

insert into role_rules(id_role, id_rule) values(1, 1);
insert into role_rules(id_role, id_rule) values(1, 2);
insert into role_rules(id_role, id_rule) values(1, 3);
insert into role_rules(id_role, id_rule) values(2, 2);
insert into role_rules(id_role, id_rule) values(2, 3);
insert into role_rules(id_role, id_rule) values(3, 1);
insert into role_rules(id_role, id_rule) values(3, 3);

insert into user1(user_name, id_item) values('user_name_1', 1);
insert into user1(user_name, id_item) values('user_name_2', 2);
insert into user1(user_name, id_item) values('user_name_3', 3);

select * from item


------------------

drop table comments;
drop table attachs;
drop table role_rules;
drop table rules;

drop table user1;

drop table item;


drop table state;
drop table category;
drop table role;





