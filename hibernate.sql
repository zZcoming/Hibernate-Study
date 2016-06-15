-- mysql dialect

drop table if exists t_student;

create table t_student (
  s_id integer not null auto_increment,
  city varchar(255),
  county varchar(255),
  province varchar(255),
  birthday datetime,
  gender varchar(255),
  sname varchar(255),
  primary key (s_id)
);