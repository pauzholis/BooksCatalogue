CREATE TABLE IF NOT EXISTS book (
  id      bigint       NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
  version INTEGER      NOT NULL,
  name    varchar(255) NOT NULL,
  CONSTRAINT book_pkey PRIMARY KEY (id)
);