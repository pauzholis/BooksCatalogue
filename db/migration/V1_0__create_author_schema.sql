CREATE TABLE IF NOT EXISTS author (
  id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
  last_name varchar(255) NOT NULL,
  first_name varchar(255) NOT NULL,
  middle_name varchar(255) NOT NULL,
  CONSTRAINT author_pkey PRIMARY KEY (id)
);