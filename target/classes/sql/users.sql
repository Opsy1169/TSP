-- Table: workingschema.users

-- DROP TABLE workingschema.users;

CREATE TABLE users
(
    user_id bigint NOT NULL DEFAULT nextval('Users_user_id_seq'::regclass),
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

ALTER TABLE users
    OWNER to postgres;