.
select * from product p, type t
where 1 = 1
and p.type_id = t.id
and t.name = 'СЫР';

2.
select * from product
where p.name like '%мороженое%';

3.
select * from product
where expired_date between date_trunc('month', CURRENT_DATE) + interval '1 month' and date_trunc('month', CURRENT_DATE) + interval '2 month - 1 second';

4.
select * from product where price = (select max(price) from product);

5.
select * from product p, type t
where 1 = 1
and p.type_id = t.id
and t.type = 1;

6.
select * from product p, type t
where 1 = 1
and p.type_id = t.id
and t.name in ('СЫР, 'МОЛОКО');

7.
select t.name cnt from product p, type t
where p.type_id = t.id
group by t.name
having count(*) < 10;

8.
select t.name, p.* from product p, type t
where p.type_id = t.id;

