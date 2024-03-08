package sh.radical.testingid.controllers;

import io.swagger.v3.oas.annotations.Parameter;
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
import sh.radical.testingid.entities.Context;
import sh.radical.testingid.entities.PagedResponse;
import sh.radical.testingid.models.Car;
import sh.radical.testingid.services.CarService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/cars")
public class CarQueryController {

	@Autowired
	CarService carService;

	@GetMapping
	public PagedResponse<Car> selectAll(
		@Parameter(hidden = true) Context context,
		@RequestParam(value = "filters", required = false) String filters,
		@RequestParam(value = "sort", required = false) String sort,
		@RequestParam(value = "limit", required = false) Integer limit,
		@RequestParam(value = "offset", required = false) Integer offset
	) {
		log.info(
			"fetching values with pagelimit: {}, offset: {} for the filters: {} & sort: {}",
			limit,
			offset,
			filters,
			sort
		);
		var carsPageResponse = carService.getAll(
			context,
			filters,
			sort,
			limit,
			offset
		);
		return carsPageResponse;
	}

	@GetMapping(value = "/{ID}")
	public Car get(
		@Parameter(hidden = true) Context context,
		@PathVariable(value = "ID") String ID
	) {
		log.info("Received a get request for Car {} ", ID);
		Car existingCar = carService.get(context, ID);
		log.info("Get request for Car {} is complete ", ID);
		return existingCar;
	}
}
