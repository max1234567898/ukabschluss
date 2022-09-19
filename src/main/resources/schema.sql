DROP TABLE IF EXISTS MEMBER CASCADE;
CREATE TABLE MEMBER
(
    id        UUID,
    firstname VARCHAR(200) NOT NULL,
    lastname  VARCHAR(200) NOT NULL,
    email     VARCHAR(200) NOT NULL,
    password  VARCHAR(200) NOT NULL,
    is_admin  BOOLEAN      NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS PLACE CASCADE;
CREATE TABLE PLACE
(
    id       UUID,
    location VARCHAR(200),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS BOOKING CASCADE;
CREATE TABLE BOOKING
(
    id          UUID,
    startDate   TIMESTAMP NOT NULL,
    endDate     TIMESTAMP NOT NULL,
    is_accepted BOOLEAN   NOT NULL DEFAULT FALSE,
    member_id   UUID,
    place_id    UUID,

    FOREIGN KEY (member_id) REFERENCES MEMBER (id),
    FOREIGN KEY (place_id) REFERENCES PLACE (id),
    PRIMARY KEY (id)
);