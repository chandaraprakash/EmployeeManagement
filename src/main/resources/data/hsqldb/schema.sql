DROP TABLE Employee IF EXISTS;

CREATE TABLE Employee (
	employee_id VARCHAR(40) NOT NULL
	, name VARCHAR(100) NOT NULL
	, department VARCHAR(100) NOT NULL
	, PRIMARY KEY(employee_id)
);