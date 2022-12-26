CREATE TABLE cards
(
    id       SERIAL PRIMARY KEY,
    ability  VARCHAR(1024),
    series   VARCHAR(255),
    cost     INTEGER NOT NULL,
    name     VARCHAR(255),
    power    INTEGER NOT NULL,
    url      VARCHAR(255),
    is_owned BOOLEAN
);
