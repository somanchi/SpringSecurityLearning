package sh.radical.school.controllers;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sh.radical.school.entities.Context;
import sh.radical.school.models.Employee;
import sh.radical.school.services.EmployeeService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/employees")
public class EmployeeQueryController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping(value = "/{employeeId}")
	public Employee get(@PathVariable(value = "employeeId") String employeeId, Context context) {
		log.info("Received a get request for Employee {} ", employeeId);
		//Context context = new Context();
		Employee existingEmployee = employeeService.get(context, employeeId);
		log.info("Get request for Employee {} is complete ", employeeId);
		return existingEmployee;
	}

	@GetMapping
	public List<Employee> selectAll(
		@RequestParam(value = "filters") String filters,
		@RequestParam(value = "sort") String sort,
		Context context
	) {
		log.info("fetching all values for the filters: {}", filters);
		//Context context = new Context();
		List<Employee> existingemployees = employeeService.getAll(
			context,
			filters,
			sort
		);
		log.info("fetched values matching for filters: {}", filters);
		return existingemployees;
	}
}
