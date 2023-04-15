#SELECT employee.first_name, `T`.position_name, department.department_name 
#FROM position_employee `position`
#INNER JOIN employee employee ON `position`.employee_id = employee.employee_id
#INNER JOIN `position` `T` ON `position`.position_id = `T`.position_id
#INNER JOIN department department ON department.department_Id = employee.department_id

#SELECT `R`.role_id, `R`.role_name ,`M`.module_name FROM module_role `Mr`
#INNER JOIN module `M` ON `M`.module_id = `Mr`.module_id
#INNER JOIN role `R`ON `R`.role_id = `Mr`.role_id;

#SELECT employee.id ,employee.last_name,employee.middle_name,employee.first_name, employee.dob, employee.email, employee.telephone, employee.address, employee.on_leave, employee.description,
#		 department.department_name,
#		 all_position_name.position_name,
#		 salary.value_money,
#		 value_money_principal.value_money_update AS 'principal'
#FROM position_employee `N`
#INNER JOIN employee  ON `N`.employee_id = employee.id
#INNER JOIN department  ON department.id = employee.department_id
#INNER JOIN all_position_name ON all_position_name.employee_id = employee.id
#INNER JOIN salary  ON salary.employee_id  = employee.id
#INNER JOIN value_money_principal ON value_money_principal.employee_id = employee.id
#WHERE MONTH(salary.time_to_pay) = MONTH(CURRENT_TIMESTAMP())
#GROUP BY employee.id;

#SELECT `P`.position_name, `T`.title FROM task_position `K` 
#INNER JOIN task `T` ON `T`.id = `K`.task_id
#INNER JOIN `position` `P` ON `P`.id = `K`.position_id

#SELECT employee.first_name ,SUM(value_money) FROM employee
#INNER JOIN principal ON principal.employee_id = employee.id
#GROUP BY principal.employee_id

#CREATE VIEW value_money_principal AS
#SELECT employee_id, sum(current_money) AS value_money_update
#FROM (
#	SELECT 	employee_id, 
#		case 
#			WHEN `type` = 'bonus' then (   value_money)
#			WHEN `type` = 'vrg' then ( - value_money)
#		END AS current_money		
#	FROM principal `P`
#) `P`
#GROUP BY employee_id;
#SELECT * FROM value_money_principal

#CREATE VIEW all_position_name AS
#SELECT employee.id AS employee_id, GROUP_CONCAT(`position`.position_name SEPARATOR  ', ') AS position_name
#FROM position_employee
#INNER JOIN `position` ON `position`.id = position_employee.position_id
#INNER JOIN employee ON employee.id = position_employee.employee_id
#GROUP BY employee.id;

#SELECT * FROM all_position_name

#SELECT salary.*,
#		 employee.last_name, employee.middle_name, employee.first_name
#FROM salary
#INNER JOIN employee ON employee.id = salary.employee_id
#ORDER BY time_to_pay ASC

#SELECT id FROM department WHERE department_name = 'Finance and Accounting'

#SELECT MONTH(hire_date) AS Month, COUNT(id) AS Quality employee
#FROM employee
#WHERE STATUS = 0 AND YEAR(hire_date) = 2023
#GROUP BY MONTH(hire_date)
#ORDER BY MONTH(hire_date);

#SELECT (CONCAT(SUM(value_money)  SUM(value_money_reward),'$')) AS 'Money', MONTH(created_at) AS 'Month'
#FROM salary
#WHERE YEAR(created_at) =2023
#GROUP BY MONTH(created_at)
#ORDER BY  MONTH(created_at)

#SELECT COUNT(`E`.department_id)AS 'Quatity employee', `D`.department_name
#FROM employee `E`
#INNER JOIN department `D` ON `E`.department_id = `D`.id
#GROUP BY department_id
#ORDER BY department_id

#SELECT COUNT(id) AS 'Total'
#FROM employee
#WHERE flag = 0 AND `status` = 0

#SELECT ROUND(AVG(YEAR(CURDATE())-YEAR(dob)),2) AS 'avg'
#FROM employee

#SELECT COUNT(gender) AS 'total'
#FROM employee
#WHERE gender = 'female'

#SELECT module.module_name
#FROM module_role
#INNER JOIN role ON role.id = module_role.role_id
#INNER JOIN module ON module.id = module_role.module_id
#INNER JOIN employee ON employee.role_id = role.id
#WHERE employee.role_id = 1
#GROUP BY module.module_name
#ORDER BY module.module_name


SELECT COUNT(task.title)AS 'title task', task.`status`, MONTH(task.created_at) AS 'month', department.department_name
FROM task_department
INNER JOIN task ON task.id = task_department.task_id
INNER JOIN department ON task_department.department_id = department.id
WHERE department.id = 3
GROUP BY  MONTH(task.created_at), task.`status`
ORDER BY  MONTH(task.created_at), task.`status`;

SELECT COUNT(task.title)AS 'title task', task.`status`, MONTH(task.created_at) AS 'month' 
FROM task_department
INNER JOIN task ON task.id = task_department.task_id
INNER JOIN department ON task_department.department_id = department.id
WHERE department.id = 3 
GROUP BY  MONTH(task.created_at), task.`status`

#SELECT TP.id,TP.position_id,TP.task_id,T.title,P.position_name FROM hrm.task_position AS TP 
#INNER JOIN hrm.`position` AS P ON P.id=TP.position_id
#INNER JOIN hrm.task AS T ON T.id=TP.task_id  
#WHERE T.flag =0
#ORDER  BY TP.id DESC

#SELECT *
#FROM salary
#WHERE salary.employee_id = 2
#ORDER BY salary.created_at desc;

#SELECT SUM(value_money) 
#FROM salary
#INNER JOIN employee ON employee.id = salary.employee_id
#WHERE salary.employee_id = 2
#GROUP BY salary.employee_id

#SELECT task.id, task.title, department.department_name, task.assignee, task.priority, task.deadline, task.`status`,
#		 CONCAT(employee.last_name, " ", employee.middle_name, " ", employee.first_name) AS 'assignee name'
#FROM task
#INNER JOIN task_department ON task_department.task_id = task.id
#INNER JOIN department ON task_department.department_id = department.id
#INNER JOIN employee ON employee.id = task.assignee
#WHERE department.id = 3
#ORDER BY task.created_at desc

#SELECT task.created_at, task.title, task.`status`
#FROM task
#INNER JOIN task_department ON task_department.task_id = task.id
#INNER JOIN department ON task_department.department_id = department.id
#INNER JOIN employee ON employee.id = task.assignee
#WHERE DAY(task.created_at) = 31 AND MONTH(task.created_at) = 3 AND  department.id = 3
