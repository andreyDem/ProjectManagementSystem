# 5. Найти клиента (customer), который приносит меньше
# всего прибыли компании (company) для каждой из компаний .

USE projectManagementDB;

CREATE TEMPORARY TABLE tmp
  SELECT
    companies.name,
    customers.id,
    sum(projects.cost) summ
  FROM companies
    JOIN projects ON projects.company_id = companies.id
    JOIN customers ON customers.id = projects.customer_id
  GROUP BY companies.name, customers.name;
SELECT * FROM tmp;

SELECT
  tmp.name,
  customers.*,
  min(tmp.summ)
FROM tmp, customers, companies
WHERE tmp.name = companies.name AND customers.id = tmp.id
GROUP BY companies.name