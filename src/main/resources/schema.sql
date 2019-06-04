DROP TABLE auth;

CREATE TABLE auth (
    id int primary key auto_increment,
    key varchar(50),
    accessToken varchar(50)
);