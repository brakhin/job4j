WITH RECURSIVE
line AS (
    SELECT 1 AS l
	UNION
    SELECT l+1 AS l FROM line WHERE l < 3
),
col AS (
    SELECT 1 AS c
	UNION
    SELECT c+1 AS c FROM col WHERE c < 3
)
INSERT INTO hall(line, col)
SELECT l, c FROM line, col order by l, c
