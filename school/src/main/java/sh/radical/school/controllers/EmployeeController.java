package sh.radical.school.controllers;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.radical.school.entities.Context;
import sh.radical.school.inputs.CreateEmployeeInput;
import sh.radical.school.inputs.UpdateEmployeeInput;
import sh.radical.school.models.Employee;
import sh.radical.school.services.EmployeeService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping
	public ResponseEntity create(
		@Valid @RequestBody CreateEmployeeInput createEmployeeInput,
		Context context
	) {
		//Context context = new Context();
		log.info("Received a new create request");
		var id = Employee.getNewEmployeeId();
		employeeService.create(context, createEmployeeInput, id);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/v1/employees/" + id);
		log.info("Create request for Employee - {} is complete", id);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@PutMapping(value = "/{employeeId}")
	public ResponseEntity update(
		@PathVariable(value = "employeeId") String employeeId,
		@Valid @RequestBody UpdateEmployeeInput updateEmployeeInput,
		Context context
	) {
		log.info("Received a update request for Employee {} ", employeeId);
		//Context context = new Context();
		employeeService.update(context, employeeId, updateEmployeeInput);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/v1/employees/" + employeeId);
		log.info("Update request for Employee {} is complete", employeeId);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{employeeId}")
	public void delete(@PathVariable(value = "employeeId") String employeeId, Context context ) {
		log.info("Received a delete request for Employee {} ", employeeId);
		//Context context = new Context();
		employeeService.delete(context, employeeId);
		log.info("Delete request completed for Employee {} ", employeeId);
	}
}
