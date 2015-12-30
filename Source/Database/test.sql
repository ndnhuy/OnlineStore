DROP TABLE IF EXISTS EMP_MASTER;
CREATE TABLE EMP_MASTER (
	Emp_code TEXT,
	EmpName TEXT,
	Salary REAL,
	Doj DATE
);

INSERT INTO EMP_MASTER VALUES('5000', 'Kumar', 450000, '2000-01-01');

select Emp_code, EmpName, Salary, Doj from emp_master where Salary > 5000;