-- CREATE TABLE _user
-- (
--     id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
--     username varchar(100) NOT NULL,
--     year_of_birth int,
--     password varchar NOT NULL,
--     role varchar(30)
-- );

ALTER TABLE _user
ADD COLUMN state VARCHAR(30);

CREATE TABLE confirmation_code
(
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    code varchar NOT NULL UNIQUE,
    user_id int references _user,
    date_time date
);



