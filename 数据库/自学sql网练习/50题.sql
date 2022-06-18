-- 1-10题

# 1 查询" 01 "课程比" 02 "课程成绩高的学生的信息及课程分数
SELECT * FROM(SELECT SID,score FROM sc WHERE sc.CID='01') AS t1,
(SELECT SID,score FROM sc WHERE sc.CId='02')AS t2
WHERE t1.SId=t2.SId and t1.score>t2.score;

# 查询同时存在" 01 "课程和" 02 "课程的情况
SELECT * FROM(SELECT SID,score FROM sc WHERE sc.CID='01') AS t1,
(SELECT SID,score FROM sc WHERE sc.CId='02')AS t2
WHERE t1.SId=t2.SId;

# 1.2 查询存在" 01 "课程但可能不存在" 02 "课程的情况(不存在时显示为 null )
SELECT *FROM(select SID,score FROM sc WHERE sc.CID='01') AS t1 left join
(SELECT SID,score FROM sc WHERE sc.CId='02')AS t2
on t1.SID=t2.SID;

# 1.3 查询不存在" 01 "课程但存在" 02 "课程的情况
SELECT * FROM sc a WHERE a.CID='02' AND NOT EXISTS 
(SELECT * FROM sc b WHERE b.CID='01' and a.SID=b.SID);

SELECT *from sc WHERE sc.CID='02' and sc.sid not in
(SELECT SID FROM sc WHERE sc.CId='01');

#查询平均成绩大于等于 60 分的同学的学生编号和学生姓名和平均成绩
SELECT t1.SID,Sname,t1.avgscore FROM student inner join(
SELECT sc.SId,AVG(sc.score) as avgscore FROM sc
group by sc.SID HAVING AVG(sc.score)>=60) 
as t1 on student.SId=t1.SId;

#查询在 SC 表存在成绩的学生信息
SELECT DISTINCT student.* FROM student,sc 
WHERE student.SID=sc.SID;

# 查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩(没成绩的显示为null)
SELECT student.SID,Sname, t1.count ,t1.sum FROM student
left join(SELECT sc.SID sid1,count(CID) count ,SUM(score) sum from sc
GROUP BY sc.SID )as t1 on student.SId=t1.sid1;

#查有成绩的学生信息
SELECT * from student WHERE student.SID in
(SELECT sc.SID FROM sc );

SELECT * FROM student WHERE EXISTS
(SELECT sc.SID FROM sc WHERE sc.SID=student.SID);

#查询「李」姓老师的数量
SELECT count(TID) FROM TEACHER
WHERE teacher.Tname LIKE '李%';

#查询学过「张三」老师授课的同学的信息
SELECT student.* FROM student,sc,teacher,course
WHERE teacher.Tname='张三' AND
teacher.TID=course.TID AND
sc.CID=course.CID AND
sc.SID=student.SID;

# ！！！！！
# 查询没有学全所有课程的同学的信息 
# 用笛卡尔积可以把什么课都没选的同学查询出来
# ！！！！！
SELECT DISTINCT student.* FROM 
(SELECT student.SID,course.CID from student,course)as t1
left join (SELECT sc.SID,sc.CID from sc)as t2
on t1.SID=t2.sid and t1.CID=t2.CID,student
WHERE t2.CID is null
and t1.SID=student.SID;

#笛卡尔积
SELECT DISTINCT * FROM 
(SELECT student.SID,course.CID from student,course)as t1
left join (SELECT sc.SID,sc.CID from sc)as t2
on t1.SID=t2.sid and t1.CID=t2.CID,student
WHERE t2.CID is null and t1.SID=student.SID;

#8.查询至少有一门课与学号为" 01 "的同学所学相同的同学的信息
SELECT student.* from student 
WHERE student.SID in
(SELECT A.SID from sc A where A.CID in
(SELECT B.CID from sc B where B.SID='01')
) and student.SID !='01';

select DISTINCT student.* from  sc ,student
where sc.CId in (select CId from sc where sc.SId='01')
and   sc.SId=student.SId and student.SID!='01';

#9.查询和" 01 "号的同学学习的课程完全相同的其他同学的信息
select *from student where SID not in 
(select distinct t1.SID from
(select * from student,(select distinct CID from sc where SID='01')as t2)as t1
LEFT JOIN sc on t1.SId=sc.SId and t1.CId=sc.CId
where sc.SId is null );

# 查询没学过"张三"老师讲授的任一门课程的学生姓名
SELECT Sname FROM student a
WHERE NOT EXISTS (SELECT *from sc,teacher,course
WHERE teacher.TID=course.TID AND
course.CID=sc.CID AND
a.SID=sc.SID AND
teacher.Tname='张三');

select sname from student where student.SId not in 
(select student.SId from student 
left join sc on student.SId=sc.SId 
where EXISTS (select * from teacher ,course
where teacher.Tname='张三'
and  teacher.TId=course.TId
and  course.CId=sc.CId));

# 11-20
#查询两门及其以上不及格课程的同学的学号，姓名及其平均成绩

SELECT student.SID,student.sname, savg FROM student
LEFT JOIN (SELECT avg(sc.score) savg,sc.sid tsid from sc GROUP BY sc.SID 
HAVING sum(case when sc.score<60 then 1 else 0 end)>=2)as t1 on student.sid=tsid 
where savg is not null;
