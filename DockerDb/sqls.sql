
CREATE DATABASE blog
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    --LC_COLLATE = 'Russian_Russia.1251'
    --LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
	
	
	
	

CREATE SCHEMA workingschema
    AUTHORIZATION postgres;
	
	
	
	
	
CREATE TABLE workingschema.articles
(
    article_id bigint NOT NULL DEFAULT nextval('workingschema."Articles_article_id_seq"'::regclass),
    title character varying(128) COLLATE pg_catalog."default",
    publishdate date,
    author bigint NOT NULL DEFAULT nextval('workingschema."Articles_author_seq"'::regclass),
    article_body text COLLATE pg_catalog."default",
    category bigint,
    CONSTRAINT "Articles_pkey" PRIMARY KEY (article_id),
    CONSTRAINT "article-category" FOREIGN KEY (category)
        REFERENCES workingschema.categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "article-user" FOREIGN KEY (author)
        REFERENCES workingschema.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE workingschema.articles
    OWNER to postgres;
	
	
	
	
	
	
-- Table: workingschema.categories

-- DROP TABLE workingschema.categories;

CREATE TABLE workingschema.categories
(
    id bigint NOT NULL,
    category character varying(128) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT categories_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE workingschema.categories
    OWNER to postgres;
COMMENT ON TABLE workingschema.categories
    IS 'Таблица с категориями
id -- id
category -- категория';





-- Table: workingschema.comments

-- DROP TABLE workingschema.comments;

CREATE TABLE workingschema.comments
(
    comment_id bigint NOT NULL DEFAULT nextval('comments_id_seq'::regclass),
    body character varying(512) COLLATE pg_catalog."default" NOT NULL,
    author_id bigint NOT NULL DEFAULT nextval('comments_author_id_seq'::regclass),
    date date,
    article integer NOT NULL,
    "time" timestamp without time zone,
    string_format_time character varying(32) COLLATE pg_catalog."default",
    CONSTRAINT "none" PRIMARY KEY (comment_id),
    CONSTRAINT "user" FOREIGN KEY (author_id)
        REFERENCES workingschema.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE workingschema.comments
    OWNER to postgres;
COMMENT ON TABLE workingschema.comments
    IS 'asd';
	
	
-- Table: workingschema.users

-- DROP TABLE workingschema.users;

CREATE TABLE workingschema.users
(
    user_id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    login character varying(64) COLLATE pg_catalog."default" NOT NULL,
    password character varying(64) COLLATE pg_catalog."default" NOT NULL,
    isadmin boolean,
    ismoder boolean,
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE workingschema.users
    OWNER to postgres;
