package sh.radical.selectallinteger.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import sh.radical.selectallinteger.entities.Context;
import sh.radical.selectallinteger.inputs.CreateStundentInput;
import sh.radical.selectallinteger.inputs.UpdateStundentInput;
import sh.radical.selectallinteger.models.Student;
import sh.radical.selectallinteger.services.StundentService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/stundents")
public class StundentController {

	@Autowired
	StundentService stundentService;

	@PostMapping
	public ResponseEntity create(
		@Parameter(hidden = true) Context context,
		@Valid @RequestBody CreateStundentInput createStundentInput
	) {
		log.info("Received a new create request");
		var id = Student.getNewStundentId();
		stundentService.create(context, createStundentInput, id);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/v1/stundents/" + id);
		log.info("Create request for Stundent - {} is complete", id);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@PutMapping(value = "/{stundentId}")
	public ResponseEntity update(
		@Parameter(hidden = true) Context context,
		@PathVariable(value = "stundentId") String stundentId,
		@Valid @RequestBody UpdateStundentInput updateStundentInput
	) {
		log.info("Received a update request for Stundent {} ", stundentId);
		stundentService.update(context, stundentId, updateStundentInput);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/v1/stundents/" + stundentId);
		log.info("Update request for Stundent {} is complete", stundentId);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{stundentId}")
	public void delete(
		@Parameter(hidden = true) Context context,
		@PathVariable(value = "stundentId") String stundentId
	) {
		log.info("Received a delete request for Stundent {} ", stundentId);
		stundentService.delete(context, stundentId);
		log.info("Delete request completed for Stundent {} ", stundentId);
	}
}
