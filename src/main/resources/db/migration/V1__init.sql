CREATE TABLE IF NOT EXISTS test_db.users (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;