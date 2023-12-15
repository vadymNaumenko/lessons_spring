CREATE TABLE _user
(
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    username varchar(100) NOT NULL,
    year_of_birth int,
    password varchar NOT NULL,
    role varchar(30)
);


-- CREATE TABLE _user
-- (
--     id            int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
--     username      varchar(100) NOT NULL,
--     year_of_birth int          NOT NULL,
--     password      varchar      NOT NULL,
--     role          varchar(30)  NOT NULL
-- );