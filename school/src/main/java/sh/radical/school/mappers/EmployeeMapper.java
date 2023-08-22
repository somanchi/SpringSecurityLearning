package sh.radical.school.mappers;

import org.springframework.stereotype.Component;
import sh.radical.school.entities.Context;
import sh.radical.school.inputs.CreateEmployeeInput;
import sh.radical.school.inputs.UpdateEmployeeInput;
import sh.radical.school.models.Employee;

@Component
public class EmployeeMapper {

	public Employee createEmployee(
		Context context,
		CreateEmployeeInput createEmployeeInput,
		String employeeId
	) {
		Employee employee = new Employee();
		employee.setName(createEmployeeInput.name);
		employee.setAge(createEmployeeInput.age);
		employee.setGender(createEmployeeInput.gender);
		employee.setEmployeeId(employeeId);
		return employee;
	}

	public Employee updateEmployee(
		Context context,
		String employeeId,
		UpdateEmployeeInput updateEmployeeInput,
		Employee existingEmployee
	) {
		existingEmployee.setName(updateEmployeeInput.name);
		existingEmployee.setAge(updateEmployeeInput.age);
		existingEmployee.setGender(updateEmployeeInput.gender);
		return existingEmployee;
	}
}
