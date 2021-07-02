CREATE TABLE user_books (
  id bigint NOT NULL AUTO_INCREMENT,
  user_id bigint NOT NULL,
  book_id bigint NOT NULL,
  create_time datetime(6) NOT NULL,
  update_time datetime(6) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_6dotkott2kjsp8vw4d0m23e7 (user_id),
  UNIQUE KEY UK_r43af9ap4edm43mmtq0r4dj6 (book_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci