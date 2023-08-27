package sh.radical.office.services;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sh.radical.office.entities.Context;
import sh.radical.office.exceptions.EmployeeNotFound;
import sh.radical.office.inputs.CreateEmployeeInput;
import sh.radical.office.inputs.UpdateEmployeeInput;
import sh.radical.office.mappers.EmployeeMapper;
import sh.radical.office.models.Employee;
import sh.radical.office.repositories.EmployeeRepository;

@Service
@Slf4j
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeMapper employeeMapper;

	public void delete(Context context, String employeeId) {
		Optional<Employee> existingEmployeeData = employeeRepository.findById(
			employeeId
		);

		if (existingEmployeeData.isEmpty()) {
			throw new EmployeeNotFound();
		}
		employeeRepository.deleteById(employeeId);
	}

	public Employee update(
		Context context,
		String employeeId,
		UpdateEmployeeInput updateEmployeeInput
	) {
		Optional<Employee> existingEmployeeData = employeeRepository.findById(
			employeeId
		);

		if (existingEmployeeData.isEmpty()) {
			throw new EmployeeNotFound();
		}
		Employee updatedEmployee = employeeMapper.updateEmployee(
			context,
			employeeId,
			updateEmployeeInput,
			existingEmployeeData.get()
		);
		Employee savedEmployee = employeeRepository.save(updatedEmployee);
		return savedEmployee;
	}

	public Employee create(
		Context context,
		CreateEmployeeInput createEmployeeInput,
		String employeeId
	) {
		Employee employee = employeeMapper.createEmployee(
			context,
			createEmployeeInput,
			employeeId
		);
		Employee createdEmployee = employeeRepository.save(employee);
		return createdEmployee;
	}

	public Employee get(Context context, String employeeId) {
		Optional<Employee> existingEmployeeData = employeeRepository.findById(
			employeeId
		);
		if (existingEmployeeData.isEmpty()) {
			throw new EmployeeNotFound();
		}
		return existingEmployeeData.get();
	}
}
