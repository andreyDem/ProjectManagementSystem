USE projectManagementDB;

-- add field cost to projects tab
ALTER TABLE projects
  ADD cost INT NOT NULL
  AFTER name;

/**
- update all fields cost in tab projects
- if appeared some errors about safe mode use:
- SET SQL_SAFE_UPDATES = 0;
- after working fine
 */
UPDATE projects p
SET p.cost = (
  SELECT sum(d.salary)
  FROM developers d
  WHERE d.project_id = p.id
);


