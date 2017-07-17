USE projectManagementDB;

-- insert data into table customers
INSERT INTO customers VALUES (1, 'Regeneron Pharmaceuticals Inc');
INSERT INTO customers VALUES (2, 'Abbott Laboratories');
INSERT INTO customers VALUES (3, 'Biogen');
INSERT INTO customers VALUES (4, 'Eli Lilly & Co');
INSERT INTO customers VALUES (5, 'Bayer');
INSERT INTO customers VALUES (6, 'Roche');

-- insert data into table companies
INSERT INTO companies VALUES (1, 'LuxSoft');
INSERT INTO companies VALUES (2, 'InfoPulse');
INSERT INTO companies VALUES (3, 'EPAM');
INSERT INTO companies VALUES (4, 'GlobalLogic');
INSERT INTO companies VALUES (5, 'Ciklum');
INSERT INTO companies VALUES (6, 'PlayTech');

-- insert data into table skills
INSERT INTO skills VALUES (1, 'Java');
INSERT INTO skills VALUES (2, 'SQL');
INSERT INTO skills VALUES (3, 'Python');
INSERT INTO skills VALUES (4, 'C++');
INSERT INTO skills VALUES (5, 'C#');
INSERT INTO skills VALUES (6, 'JavaScript');
INSERT INTO skills VALUES (7, 'Tomcat');
INSERT INTO skills VALUES (8, 'Jira');
INSERT INTO skills VALUES (9, 'Trello');
INSERT INTO skills VALUES (10, 'Scrum');

-- insert data into table projects
INSERT INTO projects VALUES (1, 'Search people app', 1, 1);
INSERT INTO projects VALUES (2, 'Android game app', 2, 2);
INSERT INTO projects VALUES (3, 'Stealth gadget', 3, 3);
INSERT INTO projects VALUES (4, 'Portable teleport station', 4, 4);
INSERT INTO projects VALUES (5, 'Collector of cosmic energy', 5, 5);
INSERT INTO projects VALUES (6, 'Search for life extension', 6, 6);

-- insert data into table developers
INSERT INTO developers VALUES (1, 'Alexey', 1, 1);
INSERT INTO developers VALUES (2, 'Andrey', 2, 2);
INSERT INTO developers VALUES (3, 'Anton', 3, 3);
INSERT INTO developers VALUES (4, 'Vadym', 4, 4);
INSERT INTO developers VALUES (5, 'Katya', 5, 5);
INSERT INTO developers VALUES (6, 'Slava', 6, 6);
INSERT INTO developers VALUES (7, 'Pavel', 1, 1);
INSERT INTO developers VALUES (8, 'Yuriy', 2, 2);
INSERT INTO developers VALUES (9, 'Misha', 3, 3);
INSERT INTO developers VALUES (10, 'Lena', 4, 4);

-- insert data into table developers_skills
INSERT INTO developers_skills VALUES (1, 1);
INSERT INTO developers_skills VALUES (1, 2);
INSERT INTO developers_skills VALUES (2, 1);
INSERT INTO developers_skills VALUES (2, 4);
INSERT INTO developers_skills VALUES (2, 7);
INSERT INTO developers_skills VALUES (3, 1);
INSERT INTO developers_skills VALUES (3, 3);
INSERT INTO developers_skills VALUES (3, 10);
INSERT INTO developers_skills VALUES (4, 2);
INSERT INTO developers_skills VALUES (4, 4);
INSERT INTO developers_skills VALUES (5, 8);
INSERT INTO developers_skills VALUES (5, 6);
INSERT INTO developers_skills VALUES (6, 3);
INSERT INTO developers_skills VALUES (6, 9);
INSERT INTO developers_skills VALUES (7, 1);
INSERT INTO developers_skills VALUES (7, 3);
INSERT INTO developers_skills VALUES (7, 7);
INSERT INTO developers_skills VALUES (8, 1);
INSERT INTO developers_skills VALUES (8, 2);
INSERT INTO developers_skills VALUES (8, 3);
INSERT INTO developers_skills VALUES (8, 4);
INSERT INTO developers_skills VALUES (9, 2);
INSERT INTO developers_skills VALUES (9, 10);
INSERT INTO developers_skills VALUES (10, 3);
INSERT INTO developers_skills VALUES (10, 7);
INSERT INTO developers_skills VALUES (10, 1);
