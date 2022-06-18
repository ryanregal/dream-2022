/*看到所有电影标题*/
SELECT title FROM movies;
/*看到4条电影*/
SELECT title,director FROM movies WHERE Id < 5;
/*看到电影总条数*/
SELECT count(*) FROM movies;
/*SQL计算1+1的和*/
SELECT 1+1;
/*找到在2000-2010年间year上映的电影*/
SELECT * FROM movies WHERE Year BETWEEN 2000 AND 2010;
/*找到2010（含）年之后的电影里片长小于两个小时的片子*/
SELECT * FROM movies WHERE Year>=2010 AND Length_minutes<120;

