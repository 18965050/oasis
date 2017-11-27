drop table if exists ACC_USER;

drop table if exists ACC_ACCOUNT;

drop table if exists ACC_ROLE;

drop table if exists ACC_PERMISSIOIN;

drop table if exists ACC_USER_ROLE;

drop table if exists ACC_ROLE_PERMISSION;

drop sequence if exists S_ACC_USER;
drop sequence if exists S_ACC_ROLE;
drop sequence if exists S_ACC_PERMISSIOIN;

create table ACC_USER (
	id	number(10) not null primary key,
	name varchar(100) UNIQUE,
	nick_name varchar(100),
	email varchar(100) UNIQUE,
	mobile varchar(20) UNIQUE,
	status number(1) not null,
	weibo_url varchar(100),
	douban_url varchar(100),
	signature varchar(250),
	create_time date not null,
	create_user_id number(10),
	update_time date,
	update_user_id number(10) not null
);

create table ACC_ACCOUNT(
	id	number(10) not null primary key,
	password char(120) not null,
	name varchar(100) UNIQUE,
	nick_name varchar(100),
	email varchar(100) UNIQUE,
	mobile varchar(20) UNIQUE,
	status number(1) not null
);

create table ACC_ROLE(
	id	number(10) not null primary key,
	name varchar(20) not null UNIQUE,
	keyword varchar(30) not null UNIQUE,
	description varchar(200),
	create_time date not null,
	create_user_id number(10) not null,
	update_time date,
	update_user_id number(10) not null
);

create table ACC_PERMISSIOIN(
	id	number(10) not null primary key,
	name varchar(20) not null UNIQUE,
	keyword varchar(30) not null UNIQUE,
	description varchar(200),
	create_time date not null,
	create_user_id number(10) not null,
	update_time date,
	update_user_id number(10) not null
);

create table ACC_USER_ROLE(
	user_id number(10) not null,
	role_id number(10) not null
);

create table ACC_ROLE_PERMISSION(
	role_id number(10) not null,
	pms_id number(10) not null
);

create sequence S_ACC_USER
increment by 1
start with 1
nomaxvalue
nocache;

create sequence S_ACC_ROLE
increment by 1
start with 1
nomaxvalue
nocache;

create sequence S_ACC_PERMISSIOIN
increment by 1
start with 1
nomaxvalue
nocache;