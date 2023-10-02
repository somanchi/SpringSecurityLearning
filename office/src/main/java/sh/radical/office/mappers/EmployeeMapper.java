package sh.radical.office.mappers;

import org.springframework.stereotype.Component;
import sh.radical.office.entities.Context;
import sh.radical.office.entities.UserContext;
import sh.radical.office.inputs.CreateEmployeeInput;
import sh.radical.office.inputs.UpdateEmployeeInput;
import sh.radical.office.models.Employee;

@Component
public class EmployeeMapper {

	public Employee updateEmployee(
		Context context,
		String employeeId,
		UpdateEmployeeInput updateEmployeeInput,
		Employee existingEmployee
	) {
		if (null == context) {
			context = new Context(new UserContext("anonymous"), false );
		}
		existingEmployee.setName(updateEmployeeInput.name);
		existingEmployee.setSalary(updateEmployeeInput.salary);
		return existingEmployee;
	}

	public Employee createEmployee(
		Context context,
		CreateEmployeeInput createEmployeeInput,
		String employeeId
	) {
		if (null == context) {
			context = new Context(new UserContext("anonymous"), false );
		}
		Employee employee = new Employee();
		employee.setName(createEmployeeInput.name);
		employee.setSalary(createEmployeeInput.salary);
		employee.setEmployeeId(employeeId);
		return employee;
	}
}
