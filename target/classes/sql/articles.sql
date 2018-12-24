-- Table: workingschema.articles

-- DROP TABLE workingschema.articles;

CREATE TABLE articles
(
    article_id bigint NOT NULL DEFAULT nextval('Articles_article_id_seq'::regclass),
    title character varying(128) COLLATE pg_catalog."default",
    publishdate date,
    author bigint NOT NULL DEFAULT nextval('Articles_author_seq'::regclass),
    article_body text COLLATE pg_catalog."default",
    CONSTRAINT "Articles_pkey" PRIMARY KEY (article_id),
    CONSTRAINT "article-user" FOREIGN KEY (author)
        REFERENCES users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE articles
    OWNER to postgres;