USE projectManagementDB;

-- add field salary to developers tab
alter table developers add salary int not null after name;

-- update data into field salary of developers tab
UPDATE developers SET salary = 3780 WHERE id = 1;
UPDATE developers SET salary = 4100 WHERE id = 2;
UPDATE developers SET salary = 5050 WHERE id = 3;
UPDATE developers SET salary = 3700 WHERE id = 4;
UPDATE developers SET salary = 4400 WHERE id = 5;
UPDATE developers SET salary = 4600 WHERE id = 6;
UPDATE developers SET salary = 6230 WHERE id = 7;
UPDATE developers SET salary = 4820 WHERE id = 8;
UPDATE developers SET salary = 4060 WHERE id = 9;
UPDATE developers SET salary = 4000 WHERE id = 10;
