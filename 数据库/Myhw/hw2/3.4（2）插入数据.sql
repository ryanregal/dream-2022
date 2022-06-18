USE `db3.4`;
/*向J表插入数据*/
INSERT INTO `db3.4`.`j` (`JNO`, `JNAME`, `CITY`) VALUES ('J1', '三建', '北京');
INSERT INTO `db3.4`.`j` (`JNO`, `JNAME`, `CITY`) VALUES ('J2', '一汽', '长春');
INSERT INTO `db3.4`.`j` (`JNO`, `JNAME`, `CITY`) VALUES ('J3', '弹簧厂', '天津');
INSERT INTO `db3.4`.`j` (`JNO`, `JNAME`, `CITY`) VALUES ('J4', '造船厂', '天津');
INSERT INTO `db3.4`.`j` (`JNO`, `JNAME`, `CITY`) VALUES ('J5', '机车厂', '唐山');
INSERT INTO `db3.4`.`j` (`JNO`, `JNAME`, `CITY`) VALUES ('J6', '无线电厂', '常州');
INSERT INTO `db3.4`.`j` (`JNO`, `JNAME`, `CITY`) VALUES ('J7', '半导体厂', '南京');
/*向P表插入数据*/
INSERT INTO `db3.4`.`p` (`PNO`, `PNAME`, `COLOR`, `WEIGHT`) VALUES ('P1', '螺母', '红', '12');
INSERT INTO `db3.4`.`p` (`PNO`, `PNAME`, `COLOR`, `WEIGHT`) VALUES ('P2', '螺栓', '绿', '17');
INSERT INTO `db3.4`.`p` (`PNO`, `PNAME`, `COLOR`, `WEIGHT`) VALUES ('P3', '螺丝刀', '蓝', '14');
INSERT INTO `db3.4`.`p` (`PNO`, `PNAME`, `COLOR`, `WEIGHT`) VALUES ('P4', '螺丝刀', '红', '14');
INSERT INTO `db3.4`.`p` (`PNO`, `PNAME`, `COLOR`, `WEIGHT`) VALUES ('P5', '凸轮', '蓝', '40');
INSERT INTO `db3.4`.`p` (`PNO`, `PNAME`, `COLOR`, `WEIGHT`) VALUES ('P6', '齿轮', '红', '30');
/*向s表插入数据*/
INSERT INTO `db3.4`.`s` (`SNO`, `SNAME`, `STATUS`, `CITY`) VALUES ('S1', '精益', '20', '天津');
INSERT INTO `db3.4`.`s` (`SNO`, `SNAME`, `STATUS`, `CITY`) VALUES ('S2', '盛锡', '10', '北京');
INSERT INTO `db3.4`.`s` (`SNO`, `SNAME`, `STATUS`, `CITY`) VALUES ('S3', '东方红', '30', '北京');
INSERT INTO `db3.4`.`s` (`SNO`, `SNAME`, `STATUS`, `CITY`) VALUES ('S4', '丰泰盛', '20', '天津');
INSERT INTO `db3.4`.`s` (`SNO`, `SNAME`, `STATUS`, `CITY`) VALUES ('S5', '为民', '30', '上海');
/*向spj表插入数据*/
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S1', 'P1', 'J4', '200', '1');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S1', 'P1', 'J1', '300', '2');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S1', 'P1', 'J2', '200', '3');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S1', 'P2', 'J2', '400', '4');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S2', 'P3', 'J3', '400', '5');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S2', 'P3', 'J1', '200', '6');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S2', 'P3', 'J2', '300', '7');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S2', 'P3', 'J4', '300', '8');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S2', 'P3', 'J5', '200', '9');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S2', 'P5', 'J2', '200', '10');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S3', 'P5', 'J1', '100', '11');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S3', 'P1', 'J1', '50', '12');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S4', 'P3', 'J1', '200', '13');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S4', 'P5', 'J3', '700', '14');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S4', 'P6', 'J4', '100', '15');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S5', 'P6', 'J4', '400', '16');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S5', 'P2', 'J1', '500', '17');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S5', 'P3', 'J2', '200', '18');
INSERT INTO `db3.4`.`spj` (`SNO`, `PNO`, `JNO`, `QTY`, `ID`) VALUES ('S5', 'P6', 'J4', '200', '19');
