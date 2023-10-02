package sh.radical.basicauthtest.controllers;

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
import sh.radical.basicauthtest.entities.Context;
import sh.radical.basicauthtest.inputs.CreateCarInput;
import sh.radical.basicauthtest.inputs.UpdateCarInput;
import sh.radical.basicauthtest.models.Car;
import sh.radical.basicauthtest.services.CarService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/cars")
public class CarController {

	@Autowired
	CarService carService;

	@PostMapping
	public ResponseEntity create(
		@Parameter(hidden = true) Context context,
		@Valid @RequestBody CreateCarInput createCarInput
	) {
		log.info("Received a new create request");
		var id = Car.getNewCarId();
		carService.create(context, createCarInput, id);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/v1/cars/" + id);
		log.info("Create request for Car - {} is complete", id);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@PutMapping(value = "/{carId}")
	public ResponseEntity update(
		@Parameter(hidden = true) Context context,
		@PathVariable(value = "carId") String carId,
		@Valid @RequestBody UpdateCarInput updateCarInput
	) {
		log.info("Received a update request for Car {} ", carId);
		carService.update(context, carId, updateCarInput);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/v1/cars/" + carId);
		log.info("Update request for Car {} is complete", carId);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{carId}")
	public void delete(
		@Parameter(hidden = true) Context context,
		@PathVariable(value = "carId") String carId
	) {
		log.info("Received a delete request for Car {} ", carId);
		carService.delete(context, carId);
		log.info("Delete request completed for Car {} ", carId);
	}
}
