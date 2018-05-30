DROP TABLE IF EXISTS `typeuser`;
CREATE TABLE `typeuser` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '0',
  `login` varchar(50) DEFAULT '0',
  `password` varchar(50) DEFAULT '0',
  `typeuser_id` int(5) NOT NULL,
  `id_extra` int(5),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `branch`;
CREATE TABLE `branch` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '0',
  `address` varchar(50) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '0',
  `branch_id` int(5) NOT NULL,
  `faculty_id` int(5) NOT NULL,
  `grade_visible` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '0',
  `address` varchar(50) DEFAULT '0',
  `phonenumber` varchar(50) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `faculty`;
CREATE TABLE `faculty` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT '0',
  `address` varchar(50) DEFAULT '0',
  `phonenumber` varchar(50) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `enrolment`;
CREATE TABLE `enrolment` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(50) DEFAULT '0',
  `course_id` varchar(50) DEFAULT '0',
  `atendance` double DEFAULT '0',
  `assignment` double DEFAULT '0',
  `exame` double DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

commit;

insert into `typeuser` (name) VALUES ('Administrator');
insert into `typeuser` (name) VALUES ('Faculty');
insert into `typeuser` (name) VALUES ('Student Manager');
insert into `typeuser` (name) VALUES ('Student');

insert into `user` (name,login,password,typeuser_id) VALUES ('Administrator','admin','123',1);
insert into `user` (name,login,password,typeuser_id,id_extra) VALUES ('Joe','joe','123',2,1);
insert into `user` (name,login,password,typeuser_id,id_extra) VALUES ('David','david','123',2,2);
insert into `user` (name,login,password,typeuser_id) VALUES ('Student Manager','manager','123',3);
insert into `user` (name,login,password,typeuser_id,id_extra) VALUES ('Brian','brian','123',4,1);
insert into `user` (name,login,password,typeuser_id,id_extra) VALUES ('Amanda','amanda','123',4,2);
insert into `user` (name,login,password,typeuser_id,id_extra) VALUES ('Adam','adam','123',4,3);
insert into `user` (name,login,password,typeuser_id,id_extra) VALUES ('Allan','Allan','17761',4,4);

insert into `branch` (name,address) VALUES ('Branch D2','Dublin 02');
insert into `branch` (name,address) VALUES ('Branch D8','Dublin 08');
insert into `branch` (name,address) VALUES ('Branch D6','Dublin 06');

insert into `course` (name,branch_id,faculty_id,grade_visible) VALUES ('English - Night',1,1,false);
insert into `course` (name,branch_id,faculty_id,grade_visible) VALUES ('Spanish - Day',2,2,false);

insert into `student` (name,address,phonenumber) VALUES ('Brian','Dublin 08','083 123 1234');
insert into `student` (name,address,phonenumber) VALUES ('Amanda','Dublin 03','083 123 1234');
insert into `student` (name,address,phonenumber) VALUES ('Adam','Swords','089 123 1234');
insert into `student` (name,address,phonenumber) VALUES ('Leila','Dublin 09','083 123 1234');

insert into `faculty` (name,address,phonenumber) VALUES ('Joe','Dublin 01','083 123 1111');
insert into `faculty` (name,address,phonenumber) VALUES ('David','Malahide','083 321 0000');

insert into `enrolment` (student_id,course_id,atendance,assignment,exame) VALUES (1,1,100,0,0);
insert into `enrolment` (student_id,course_id,atendance,assignment,exame) VALUES (2,1,100,0,0);

commit;