/*找到所有Toy Story系列电影*/
SELECT * FROM movies WHERE Title LIKE "TOY STORY%";
/*按导演名排重列出所有电影(只显示导演)，并按导演名正序排列*/
SELECT DISTINCT Director FROM movies ORDER BY Director ASC;
/*列出按上映年份最新上线的4部电影*/
SELECT * FROM movies ORDER BY Year DESC LIMIT 4;
/*按电影名字母序升序排列，列出前5部电影*/
SELECT*FROM movies ORDER BY Title ASC LIMIT 5;
/*按电影名字母序升序排列，列出上一题之后的5部电影*/
SELECT*FROM movies ORDER BY Title ASC LIMIT 5 OFFSET 5;
/*如果按片长排列，John Lasseter导演导过片长第3长的电影是哪部，列出名字即可*/
SELECT Title FROM movies WHERE Director="John Lasseter" ORDER BY Length_minutes DESC 
LIMIT 1 OFFSET 3;

/*列出所有在Chicago西部的城市，从西到东排序(包括所有字段)*/
SELECT * FROM north_american_cities WHERE Longitude<
(SELECT Longitude FROM north_american_cities WHERE City='Chicago')
ORDER BY Longitude ASC;
/*用人口数population排序,列出墨西哥Mexico最大的2个城市(包括所有字段)*/
SELECT * FROM north_american_cities WHERE Country="Mexico" ORDER BY Population DESC
LIMIT 2;
/*列出美国United States人口3-4位的两个城市(包括所有字段)*/
SELECT * FROM north_american_cities WHERE Country="United States" ORDER BY Population DESC
LIMIT 2 OFFSET 2;





