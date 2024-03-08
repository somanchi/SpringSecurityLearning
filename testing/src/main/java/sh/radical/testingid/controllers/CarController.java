package sh.radical.testingid.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
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
import sh.radical.testingid.entities.Context;
import sh.radical.testingid.inputs.CreateCarInput;
import sh.radical.testingid.inputs.UpdateCarInput;
import sh.radical.testingid.models.Car;
import sh.radical.testingid.services.CarService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/cars")
public class CarController {

	@Autowired
	CarService carService;

	@DeleteMapping(value = "/{ID}")
	public void delete(
		@Parameter(hidden = true) Context context,
		@PathVariable(value = "ID") String ID
	) {
		log.info("Received a delete request for Car {} ", ID);
		carService.delete(context, ID);
		log.info("Delete request completed for Car {} ", ID);
	}

	@PutMapping(value = "/{ID}")
	public ResponseEntity update(
		@Valid @Parameter(hidden = true) Context context,
		@PathVariable(value = "ID") String ID,
		@Valid @RequestBody UpdateCarInput updateCarInput
	) {
		log.info("Received a update request for Car {} ", ID);
		carService.update(context, ID, updateCarInput);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/v1/cars/" + ID);
		log.info("Update request for Car {} is complete", ID);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity create(
		@Valid @Parameter(hidden = true) Context context,
		@Valid @RequestBody CreateCarInput createCarInput
	) {
		log.info("Received a new create request");
		var id = Car.getNewID();
		carService.create(context, createCarInput, id);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/v1/cars/" + id);
		log.info("Create request for Car - {} is complete", id);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}
}
