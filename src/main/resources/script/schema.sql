CREATE DATABASE Homework003;

CREATE TABLE venues(
    venue_id SERIAL PRIMARY KEY ,
    venue_name VARCHAR(50),
    location VARCHAR(50)
);


CREATE TABLE events(
    event_id SERIAL PRIMARY KEY ,
    event_name VARCHAR(50),
    event_date TIMESTAMP,
    venue_id INT,
    CONSTRAINT venues_fk foreign key (venue_id) references venues(venue_id)
                   ON UPDATE  CASCADE ON DELETE SET NULL
);

CREATE TABLE attendees(
    attendee_id SERIAL PRIMARY KEY ,
    attendee_name VARCHAR(50),
    email VARCHAR(40)
);

CREATE TABLE event_attendee(
    id SERIAL PRIMARY KEY ,
    event_id INT,
    attendee_id INT,
    CONSTRAINT FK_event_id_eventAttendee_db FOREIGN KEY (event_id) references events(event_id) ON DELETE  CASCADE ,
    CONSTRAINT FK_attendee_id_eventAttendee_db FOREIGN KEY (attendee_id) references attendees(attendee_id) ON DELETE CASCADE
);

-- Inserting into venues table
INSERT INTO venues (venue_name, location) VALUES
                                              ('Venue 1', 'Location 1'),
                                              ('Venue 2', 'Location 2'),
                                              ('Venue 3', 'Location 3'),
                                              ('Venue 4', 'Location 4'),
                                              ('Venue 5', 'Location 5');


-- Inserting into events table
INSERT INTO events (event_name, event_date, venue_id) VALUES
                                                          ('Event 1', '2024-04-05', 1),
                                                          ('Event 2', '2024-04-06', 2),
                                                          ('Event 3', '2024-04-07', 3),
                                                          ('Event 4', '2024-04-08', 4),
                                                          ('Event 5', '2024-04-09', 5);


-- Inserting into attendees table
INSERT INTO attendees (attendee_name, email) VALUES
                                                 ('Attendee 1', 'attendee1@example.com'),
                                                 ('Attendee 2', 'attendee2@example.com'),
                                                 ('Attendee 3', 'attendee3@example.com'),
                                                 ('Attendee 4', 'attendee4@example.com'),
                                                 ('Attendee 5', 'attendee5@example.com');


-- Inserting into event_attendee table
INSERT INTO event_attendee (event_id, attendee_id) VALUES
                                                       (1,1),
                                                       (2, 2),
                                                       (3, 3),
                                                       (4, 4),
                                                       (5, 5),
                                                       (6, 6),
                                                       (7, 7),
                                                       (8, 8),
                                                       (9, 9),
                                                       (10, 10);


DELETE  FROM venues WHERE venue_id = 5;

SELECT * FROM event_attendee ea INNER JOIN attendees a on a.attendee_id = ea.attendee_id;

SELECT a.attendee_id , a.attendee_name , a.email FROM attendees a INNER JOIN  event_attendee ea ON a.attendee_id = ea.attendee_id WHERE ea.event_id = 1;

SELECT * FROM events;

INSERT INTO event_attendee(event_id, attendee_id) VALUES (1, 5);