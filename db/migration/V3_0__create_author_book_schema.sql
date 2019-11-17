CREATE TABLE IF NOT EXISTS author_book (
  author_id BIGINT NOT NULL,
  book_id BIGINT NOT NULL,
  CONSTRAINT author_book_pkey PRIMARY KEY (author_id, book_id),
  CONSTRAINT author_fkey FOREIGN KEY (author_id) REFERENCES author (id),
  CONSTRAINT book_fkey FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE INDEX ix_author_book_id ON author_book (author_id);
CREATE INDEX ix_book_author_id ON author_book (book_id);