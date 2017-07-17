DROP DATABASE IF EXISTS projectManagementDB;
CREATE DATABASE IF NOT EXISTS projectManagementDB DEFAULT CHARACTER SET utf8;
USE projectManagementDB;

-- companies
CREATE TABLE IF NOT EXISTS companies (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(25) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

-- customers
CREATE TABLE IF NOT EXISTS customers (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30),
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

-- projects
CREATE TABLE IF NOT EXISTS projects (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  company_id INT NOT NULL,
  customer_id INT NOT NULL,
  FOREIGN KEY (company_id) REFERENCES companies (id),
  FOREIGN KEY (customer_id) REFERENCES customers (id)
)
  ENGINE = InnoDB;

-- developers
CREATE TABLE IF NOT EXISTS developers (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  company_id INT NOT NULL,
  project_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (company_id) REFERENCES companies (id),
  FOREIGN KEY (project_id) REFERENCES projects (id)
)
  ENGINE = InnoDB;

-- skills
CREATE TABLE IF NOT EXISTS skills (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(25) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

-- developers_skills
CREATE TABLE IF NOT EXISTS developers_skills(
  developer_id INT NOT NULL ,
  skill_id INT NOT NULL ,
  PRIMARY KEY (developer_id, skill_id),
  FOREIGN KEY (developer_id) REFERENCES developers (id),
  FOREIGN KEY (skill_id) REFERENCES skills (id)
)
  ENGINE = InnoDB;
