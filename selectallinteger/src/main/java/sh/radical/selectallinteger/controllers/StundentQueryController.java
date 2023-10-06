package sh.radical.selectallinteger.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sh.radical.selectallinteger.entities.Context;
import sh.radical.selectallinteger.entities.PagedResponse;
import sh.radical.selectallinteger.models.Student;
import sh.radical.selectallinteger.services.StundentService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/stundents")
public class StundentQueryController {

	@Autowired
	StundentService stundentService;

	@GetMapping(value = "/{stundentId}")
	public Student get(
		@Parameter(hidden = true) Context context,
		@PathVariable(value = "stundentId") String stundentId
	) {
		log.info("Received a get request for Stundent {} ", stundentId);
		Student existingStundent = stundentService.get(context, stundentId);
		log.info("Get request for Stundent {} is complete ", stundentId);
		return existingStundent;
	}

	@GetMapping
	public PagedResponse<Student> selectAll(
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
		var stundentsPageResponse = stundentService.getAll(
			context,
			filters,
			sort,
			limit,
			offset
		);
		return stundentsPageResponse;
	}
}
