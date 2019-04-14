-- СозданиеБД
create database uml_demo;

-- Создание таблиц
create table user (
    id serial primary key,
    user_name varchar(20),
    id_role integer reference role(id),
    id_item integer unique reference item(id)
);

create table role (
    id serial primary key,
    role_name varchar(20)
);

create table rules (
    id serial primary key,
    rule_name varchar(20)
);

create table role_rules (
    id serial primary key,
    id_role integer reference role(id),
    id_rule inetegr reference rule(id)
);

create table item (
    id serial primary key,
    item_name varchar(20)
    id_category integer reference category(id),
    id_state integer reference state(id),
);

create table category (
    id serial primary key,
    category_name varchar(20)
);

create table state (
    id serial primary key,
    state_name varchar(20)
);

create table attachs (
    id serial primary key,
    attach_name varchar(20)
    id_item integer reference item(id),
);

create table comments (
    id serial primary key,
    attach_name varchar(20)
    id_item integer reference item(id),
);

-- Заполнение данными

insert category(category_name) values("category_name_1");
insert category(category_name) values("category_name_2");
insert category(category_name) values("category_name_3");

insert comment(category_name) values("comment_name_1");
insert comment(category_name) values("comment_name_2");
insert comment(category_name) values("comment_name_3");

insert state(category_name) values("state_name_1");
insert state(category_name) values("state_name_2");
insert state(category_name) values("state_name_3");

insert item(item_name, id_category, id_state) values("item_name_1", 1, 1);
insert item(item_name, id_category, id_state) values("item_name_2", 2, 1);
insert item(item_name, id_category, id_state) values("item_name_4", 1, 2);
insert item(item_name, id_category, id_state) values("item_name_4", 2, 2);

insert comments(comment_name. id_item) values("comment_name_1", 1);
insert comments(comment_name. id_item) values("comment_name_2", 2);

insert attachs(attach_name, id_item) values("attach_name_1", 1);
insert attachs(attach_name, id_item) values("attach_name_2", 2);

insert role(role_name) values("role_name_1");
insert role(role_name) values("role_name_2");
insert role(role_name) values("role_name_3");

insert rules(rule_name) values("rule_name_1");
insert rules(rule_name) values("rule_name_2");
insert rules(rule_name) values("rule_name_3");

insert role_rules(id_role, id_rules) values(1, 1);
insert role_rules(id_role, id_rules) values(1, 2);
insert role_rules(id_role, id_rules) values(1, 3);
insert role_rules(id_role, id_rules) values(2, 2);
insert role_rules(id_role, id_rules) values(2, 3);
insert role_rules(id_role, id_rules) values(3, 1);
insert role_rules(id_role, id_rules) values(3, 3);

insert user(user_name, id_item) values("user_name_1", 1);
insert user(user_name, id_item) values("user_name_2", 2);
insert user(user_name, id_item) values("user_name_3", 3);
