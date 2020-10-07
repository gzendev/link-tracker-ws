
-- Database: linktracker

-- DROP DATABASE linktracker;

CREATE DATABASE linktracker
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- Table: linktracker.link

-- DROP TABLE linktracker.link;

CREATE TABLE linktracker.link
(
    link_id bigint NOT NULL,
    expiration timestamp without time zone NOT NULL,
    original character varying(255) COLLATE pg_catalog."default" NOT NULL,
    redirect integer NOT NULL,
    shortened character varying(255) COLLATE pg_catalog."default" NOT NULL,
    token character varying(255) COLLATE pg_catalog."default" NOT NULL,
    valid integer NOT NULL,
    CONSTRAINT link_pkey PRIMARY KEY (link_id)
)

TABLESPACE pg_default;

ALTER TABLE linktracker.link
    OWNER to postgres;