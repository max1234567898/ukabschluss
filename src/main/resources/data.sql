INSERT INTO MEMBER (id, firstname, lastname, username, password_hash, is_admin)
VALUES ('9135f12e-1b66-4ee6-bbae-df37303cc154', 'Leon', 'Muster','admin','$2a$10$aDD6I9Ej5.W8busvlsdPx.JvMWyJX8cOeOfVb.3q73KH2swww/N9C', true);

INSERT INTO MEMBER (id, firstname, lastname,username, password_hash, is_admin)
VALUES ('9135f11e-1b66-4ee6-bbae-df37303cc154', 'KK', 'kouroms','mitglied', '$2a$10$aDD6I9Ej5.W8busvlsdPx.JvMWyJX8cOeOfVb.3q73KH2swww/N9C', false); -- Password: password1234

INSERT INTO PLACE (id, location)
VALUES ('4be5f5bf-8eb5-44ea-8eb5-a5e807856d09', 'Tisch 1'),
       ('e1eec954-6ef4-4926-8183-7575af189f2a', 'Meetingraum 1'),
       ('e1eec954-6ef4-4926-8183-7575af189f2b', 'Meetingraum 3');


INSERT INTO BOOKING (id, start_date, end_date, is_accepted, member_id, place_id)
VALUES ('413e2297-b84b-42ef-97ed-16a8a9d1d671', '2012-09-17 18:47:52.069','2012-09-18 18:47:52.069',true,'9135f12e-1b66-4ee6-bbae-df37303cc154', '4be5f5bf-8eb5-44ea-8eb5-a5e807856d09'),
       ('b8160463-01a0-4c7a-bd46-5b3716dbe4c6', '2012-08-17 18:47:52.069','2012-08-18 18:47:52.069',false,'9135f12e-1b66-4ee6-bbae-df37303cc154', 'e1eec954-6ef4-4926-8183-7575af189f2a');

