
// 1.
select p.name, c.name 
from person p left join company c 
on p.id_company = c.id
where c.id is null or c.id <> 5;


// 2.
select c.name, t.cnt 
from company c inner join (
	select id_company, cound(*) cnt 
	from person
	group by id_company
	having count(*) = (
		select max(count) max_count from (
			select count(*) from person group by company_id
		)
	)
)t on c.id = t.id_company;
