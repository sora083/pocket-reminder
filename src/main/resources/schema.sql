CREATE TABLE IF NOT EXISTS auth (
    id int primary key auto_increment,
    key varchar(50),
    accessToken varchar(50)
);