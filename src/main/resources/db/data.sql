insert into lecture (max_applicant_count,content,name,room,speaker,start_date_time) values (10,'강의내용','미노출강의','강의실','강연자', TIMESTAMPADD(DAY, -2, NOW()));
insert into lecture (max_applicant_count,content,name,room,speaker,start_date_time) values (10,'강의내용','12시간전 강의','강의실','강연자', TIMESTAMPADD(HOUR, -12, NOW()));
insert into lecture (max_applicant_count,content,name,room,speaker,start_date_time) values (10,'강의내용','내일 강의','강의실','강연자', TIMESTAMPADD(DAY, 1, NOW()));
insert into lecture (max_applicant_count,content,name,room,speaker,start_date_time) values (10,'강의내용','(최고인기) 7일 후 강의','강의실','강연자', TIMESTAMPADD(DAY, 7, NOW()));
insert into lecture (max_applicant_count,content,name,room,speaker,start_date_time) values (10,'강의내용','8일 이후 강의','강의실','강연자', TIMESTAMPADD(DAY, 8, NOW()));

insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (TIMESTAMPADD(DAY, -4, NOW()),'AAAAA',3);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (TIMESTAMPADD(DAY, -4, NOW()),'BBBBB',3);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'CCCCC',3);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'DDDDD',3);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'EEEEE',3);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'FFFFF',3);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'GGGGG',3);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'HHHHH',3);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'IIIII',3);

insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'AAAAA',4);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'BBBBB',4);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'CCCCC',4);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'DDDDD',4);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'EEEEE',4);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'FFFFF',4);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'GGGGG',4);
insert into apply_lecture (apply_date_time,employee_id,lecture_id) values (NOW(),'HHHHH',4);