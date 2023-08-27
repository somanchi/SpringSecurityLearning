package sh.radical.office.controllers;

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
import sh.radical.office.entities.Context;
import sh.radical.office.inputs.CreateEmployeeInput;
import sh.radical.office.inputs.UpdateEmployeeInput;
import sh.radical.office.models.Employee;
import sh.radical.office.services.EmployeeService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@DeleteMapping(value = "/{employeeId}")
	public void delete(
		Context context,
		@PathVariable(value = "employeeId") String employeeId
	) {
		log.info("Received a delete request for Employee {} ", employeeId);
		employeeService.delete(context, employeeId);
		log.info("Delete request completed for Employee {} ", employeeId);
	}

	@PutMapping(value = "/{employeeId}")
	public ResponseEntity update(
		Context context,
		@PathVariable(value = "employeeId") String employeeId,
		@Valid @RequestBody UpdateEmployeeInput updateEmployeeInput
	) {
		log.info("Received a update request for Employee {} ", employeeId);
		employeeService.update(context, employeeId, updateEmployeeInput);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/v1/employees/" + employeeId);
		log.info("Update request for Employee {} is complete", employeeId);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity create(
		Context context,
		@Valid @RequestBody CreateEmployeeInput createEmployeeInput
	) {
		log.info("Received a new create request");
		var id = Employee.getNewEmployeeId();
		employeeService.create(context, createEmployeeInput, id);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/v1/employees/" + id);
		log.info("Create request for Employee - {} is complete", id);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}
}
