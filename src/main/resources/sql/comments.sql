CREATE TABLE workingschema.comments
(
    comment_id bigint NOT NULL DEFAULT nextval('comments_id_seq'::regclass),
    body character varying(512) COLLATE pg_catalog."default" NOT NULL,
    author_id bigint NOT NULL DEFAULT nextval('comments_author_id_seq'::regclass),
    date date,
    article integer NOT NULL,
    CONSTRAINT comments_pkey PRIMARY KEY (comment_id),
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