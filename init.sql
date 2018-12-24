--
-- PostgreSQL database dump
--

-- Dumped from database version 10.4
-- Dumped by pg_dump version 10.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comments (comment_id, body, author_id) FROM stdin;
1	opsy comment	1
3	users comment	2
0	another opsy comment	1
7	qwe	1
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, login, password, isadmin, ismoder) FROM stdin;
2	user	1234	f	f
1	opsy	1169	t	f
\.


--
-- Data for Name: articles; Type: TABLE DATA; Schema: workingschema; Owner: postgres
--

COPY workingschema.articles (article_id, title, publishdate, author, article_body, category) FROM stdin;
2	best article	2018-10-15	3	First and best article you`ve ver read\r\n<h1>come see it</h1>\r\n<h2> thats why you have to see it</h2>\r\n<ol>\r\n<li>first reason</li>\r\n<li>another reason</li>\r\n</ol>\r\n<img src="http://mirpozitiva.ru/uploads/posts/2016-09/1474011210_15.jpg">\r\n<p style = "color:red" >edit</p>	11
3	shitty article	2018-10-14	4	another shitty article made not by opsy	11
9	еще одна статья	2018-11-02	3	йцуйцуйцу	11
26	after validation problems	2018-11-18	3	checkchek	11
27	Тест совмещения создания и реадктирования	2018-11-19	3	Тест совмещения создания и реадктирования отредактировано	11
30	tv article	2018-12-01	3	tv qrticle edited	3
31	prgcheck	2018-12-02	3	prgcheck	1
33	chromecheck	2018-12-02	3	chromecheck	1
34	okokokokokk	2018-12-02	3	okokokokokko	1
42	delete	2018-12-05	3	asdasdasdasdasdasd	1
44	b	\N	3	asdasd	\N
\.


--
-- Data for Name: categories; Type: TABLE DATA; Schema: workingschema; Owner: postgres
--

COPY workingschema.categories (id, category) FROM stdin;
1	IT
2	Movies
3	TV
4	Music
5	Science
6	Books
7	Media
8	Games
9	Travelling
10	Sport
11	Offtop
\.


--
-- Data for Name: comments; Type: TABLE DATA; Schema: workingschema; Owner: postgres
--

COPY workingschema.comments (comment_id, body, author_id, date, article, "time", string_format_time) FROM stdin;
65	check fuul article comments pagination	3	2018-12-05	2	2018-12-05 20:06:50.924	2018.12.05 20:06
66	one more time author fix	3	2018-12-05	2	2018-12-05 20:07:39.759	2018.12.05 20:07
67	fix	3	2018-12-05	2	2018-12-05 20:07:51.869	2018.12.05 20:07
27	check	3	2018-11-17	2	2018-11-17 16:51:36.914	2018.11.17 16:51
77	ZXC	4	2018-12-11	2	2018-12-11 20:05:37.087	2018.12.11 20:05
78	zxc	4	2018-12-11	2	2018-12-11 20:06:46.149	2018.12.11 20:06
5	opsy comment	3	2018-11-08	2	2018-11-08 16:24:34.278	2018.11.8 16:51
6	users comment	4	2018-11-09	2	2018-11-09 16:24:34.278	2018.11.9 16:51
8	another opsy comm	3	2018-11-10	3	2018-11-10 16:24:34.278	2018.11.10 16:51
19	hey you again	3	2018-11-11	2	2018-11-11 16:24:34.278	2018.11.11 16:51
20	author check	3	2018-11-12	2	2018-11-12 16:24:34.278	2018.11.12 16:51
21	check chek	3	2018-11-13	2	2018-11-13 16:24:34.278	2018.11.13 16:51
22	check\n	3	2018-11-14	2	2018-11-14 16:24:34.278	2018.11.14 16:51
23	timestamp check	3	2018-11-17	2	2018-11-17 16:24:34.278	2018.11.17 16:47
24	another timestamp	3	2018-11-17	2	2018-11-17 16:28:11.446	2018.11.17 16:48
25	format time chceck	3	2018-11-17	2	2018-11-17 16:49:58.093	2018.11.17 16:49
26	check	3	2018-11-17	2	2018-11-17 16:50:47.064	2018.11.17 16:50
28	sort comm 1	3	2018-11-17	2	2018-11-17 17:09:20.085	2018.11.17 17:09
29	sort comm 2	3	2018-11-17	2	2018-11-17 17:09:31.248	2018.11.17 17:09
30	area text	3	2018-11-17	2	2018-11-17 17:10:09.135	2018.11.17 17:10
31	area text 1	3	2018-11-17	2	2018-11-17 17:11:21.064	2018.11.17 17:11
32	text area	3	2018-11-17	2	2018-11-17 17:12:28.294	2018.11.17 17:12
33	asd	3	2018-11-17	2	2018-11-17 17:13:29.592	2018.11.17 17:13
34	qwe	3	2018-11-19	9	2018-11-19 22:04:34.689	2018.11.19 22:04
35	a	3	2018-01-01	3	2018-11-08 16:24:34.278	\N
36	a	3	2018-01-01	3	2018-11-08 16:24:34	\N
37	a	3	2018-12-01	27	2018-12-01 16:18:01.855	2018.12.01 16:18
41	a	3	2018-12-01	27	2018-12-01 16:19:34.418	2018.12.01 16:19
43	a	3	2018-12-01	27	2018-12-01 16:20:06.154	2018.12.01 16:20
44	as	3	2018-12-01	27	2018-12-01 16:20:10.753	2018.12.01 16:20
46	aa	3	2018-12-01	27	2018-12-01 16:21:52.653	2018.12.01 16:21
53	a	3	2018-12-01	27	2018-12-01 16:48:28.765	2018.12.01 16:48
54	check	3	2018-12-01	27	2018-12-01 16:49:11.945	2018.12.01 16:49
55	asd	3	2018-12-01	27	2018-12-01 16:52:17.732	2018.12.01 16:52
56	asd	3	2018-12-01	27	2018-12-01 16:53:25.666	2018.12.01 16:53
57	asd	3	2018-12-01	27	2018-12-01 16:57:23.134	2018.12.01 16:57
58	asd	3	2018-12-01	27	2018-12-01 17:00:45.662	2018.12.01 17:00
59	asd	3	2018-12-01	27	2018-12-01 17:04:01.567	2018.12.01 17:04
63	chrome check comm	3	2018-12-02	33	2018-12-02 16:13:01.292	2018.12.02 16:13
64	another one	3	2018-12-02	33	2018-12-02 16:13:08.644	2018.12.02 16:13
79	zzzzzz	4	2018-12-11	2	2018-12-11 20:07:47.129	2018.12.11 20:07
80	qwe	4	2018-12-11	2	2018-12-11 20:10:52.287	2018.12.11 20:10
81	qaz	4	2018-12-11	2	2018-12-11 20:12:02.751	2018.12.11 20:12
82	fixed	4	2018-12-11	2	2018-12-11 20:13:40.369	2018.12.11 20:13
83	truly fixed	4	2018-12-11	2	2018-12-11 20:14:40.934	2018.12.11 20:14
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: workingschema; Owner: postgres
--

COPY workingschema.users (user_id, login, password, isadmin, ismoder) FROM stdin;
5	admin	$2a$10$Gyv36AZE73fYnEXZyPoDMe7xsWaoyehWVAHRt1dGqxFO38iW1jGRO	t	f
3	opsy	$2a$10$5lVAOGwwygTvmhwurDp9eeKp5Fqkw.WWaK2d7D2UXqYyJ29s7tb82	t	f
4	user	$2a$10$UhVbOP2jbSVoSNeHA5932OlkhkGOwCoKQo.dfmNcOLNJWq6MXt80C	f	f
0	opsy1169	1	f	f
11	raw	asd	f	f
13	asd	123	f	f
15	rawcheck	1	f	f
16	zxczxczxcz	1	f	f
10	idea	edited	\N	\N
17	updatecheck	1	f	f
19	1111	$2a$10$9L8BBXvsPR6C6m6aSB/16uEInprhsZFIt7MidXH4mmn5twhLNrmNC	f	f
20	a	1	\N	\N
21	opsy1	$2a$10$Y7eN3VNBBWd28RF3xAk5W.HW6zsOMyNjxEyGrmD47vBgKHusjWrp2	f	f
22	opsy2	$2a$10$MwAIIfBgozP8jnamVszOq.T3vKtUL5l7dKQObvRm70zOYtFlgSk7a	f	f
\.


--
-- Name: comments_author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comments_author_id_seq', 2, true);


--
-- Name: comments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comments_id_seq', 83, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 22, true);


--
-- Name: Articles_article_id_seq; Type: SEQUENCE SET; Schema: workingschema; Owner: postgres
--

SELECT pg_catalog.setval('workingschema."Articles_article_id_seq"', 42, true);


--
-- Name: Articles_author_seq; Type: SEQUENCE SET; Schema: workingschema; Owner: postgres
--

SELECT pg_catalog.setval('workingschema."Articles_author_seq"', 5, true);


--
-- PostgreSQL database dump complete
--

