package sh.radical.school.services;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sh.radical.school.entities.Context;
import sh.radical.school.exceptions.EmployeeNotFound;
import sh.radical.school.inputs.CreateEmployeeInput;
import sh.radical.school.inputs.UpdateEmployeeInput;
import sh.radical.school.mappers.EmployeeMapper;
import sh.radical.school.models.Employee;
import sh.radical.school.repositories.EmployeeRepository;

@Service
@Slf4j
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeMapper employeeMapper;

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

	public Employee get(Context context, String employeeId) {
		Optional<Employee> existingEmployeeData = employeeRepository.findById(
			employeeId
		);
		if (existingEmployeeData.isEmpty()) {
			throw new EmployeeNotFound();
		}
		return existingEmployeeData.get();
	}

	public List<Employee> getAll(Context context, String filters, String sort) {
		log.info("inside findAll service method");
		return employeeRepository.findAllEmployees(filters, sort);
	}

	public void delete(Context context, String employeeId) {
		Optional<Employee> existingEmployeeData = employeeRepository.findById(
			employeeId
		);

		if (existingEmployeeData.isEmpty()) {
			throw new EmployeeNotFound();
		}
		employeeRepository.deleteById(employeeId);
	}
}
