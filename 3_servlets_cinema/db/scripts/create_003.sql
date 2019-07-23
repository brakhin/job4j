WITH RECURSIVE
  cnt_col(col) AS (
     SELECT 1
     UNION ALL
     SELECT col+1 FROM cnt_col
      LIMIT 3
  ),
  cnt_line(line) AS (
     SELECT 1
     UNION ALL
     SELECT line+1 FROM cnt_line
      LIMIT 3
  )
insert into hall(line, col)
SELECT line, col FROM cnt_line, cnt_col order by line, col