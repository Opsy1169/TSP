
INSERT INTO "users" ( "isadmin", "ismoder", "login", "password") VALUES
(	'1',	NULL,	'admin',	'$2a$10$Gyv36AZE73fYnEXZyPoDMe7xsWaoyehWVAHRt1dGqxFO38iW1jGRO'),
(	'1',	NULL,	'user',	'$2a$10$UhVbOP2jbSVoSNeHA5932OlkhkGOwCoKQo.dfmNcOLNJWq6MXt80C'),
(	NULL,	NULL,	'opsy1169',	'1'),
(	'1',	'1',	'11',	'$2a$10$vWLXqPhwVjQua06qSrUjbObgW.Wutlgt3A2Qwgg7tEhtWLN5VJG3a'),
(	NULL,	NULL,	'idea',	'edited'),
(NULL,	NULL,	'22',	'$2a$10$P6fLpTVWsMaCBKxWtgDrCeoSY67AYuprB0yoa12.Zh/Xzw9zkV/I6');

INSERT INTO "categories" ( "category") VALUES
(	'IT'),
(	'Movies'),
(	'TV'),
(	'Music'),
(	'Science'),
(	'Books'),
(	'Media'),
(	'Games'),
(	'Travelling');

INSERT INTO "articles" ( "article_body", "publishdate", "title", "author", "category") VALUES
(	'йцуйцуйцу',	'2018-10-15',	'best article',	3,	1),
(	'another shitty article made not by opsy',	'2018-10-14',	'shitty article',	4,	1),
(	'Тест совмещения создания и реадктирования отредактировано',	'2018-11-18',	'after validation problems',	2,	4);



INSERT INTO "comments" ( "body", "author_id", "article", "date", "string_format_time", "time") VALUES
(	'check fuul article comments pagination',	3,	2,	'2018-12-05',	'2018-12-05 20:06:50.924',	'2018-12-05 20:06:00'),
(	'one more time author fix',	2,	3,	'2018-12-05',	'2018-12-05 20:07:39.759 ',	'2018-12-05 20:07:00');


