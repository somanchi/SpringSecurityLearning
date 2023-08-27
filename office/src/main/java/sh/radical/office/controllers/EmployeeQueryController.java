package sh.radical.office.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.radical.office.entities.Context;
import sh.radical.office.models.Employee;
import sh.radical.office.services.EmployeeService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/employees")
public class EmployeeQueryController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping(value = "/{employeeId}")
	public Employee get(
		Context context,
		@PathVariable(value = "employeeId") String employeeId
	) {
		log.info("Received a get request for Employee {} ", employeeId);
		Employee existingEmployee = employeeService.get(context, employeeId);
		log.info("Get request for Employee {} is complete ", employeeId);
		return existingEmployee;
	}
}
