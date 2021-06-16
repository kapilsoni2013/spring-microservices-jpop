CREATE TABLE users (
  id bigint NOT NULL AUTO_INCREMENT,
  address varchar(255) DEFAULT NULL,
  age int DEFAULT NULL,
  create_time datetime(6) NOT NULL,
  email varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  update_time datetime(6) NOT NULL,
  username varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_6dotkott2kjsp8vw4d0m25fb7 (email),
  UNIQUE KEY UK_r43af9ap4edm43mmtq01oddj6 (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci