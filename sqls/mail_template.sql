create table mail_templates(id int primary key auto_increment, slug varchar(255), name varchar(100),
content text, added_date timestamp default current_timestamp, status tinyint(1));

insert into mail_templates(slug, name, content, status)
values('Enroll-Confirmation','Enrollment Confirmation','',1);