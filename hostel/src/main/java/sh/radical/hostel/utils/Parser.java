package sh.radical.hostel.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import sh.radical.hostel.entities.SearchQuery;
import sh.radical.hostel.exceptions.ParserException;
import sh.radical.hostel.utils.OperationConstants;
import sh.radical.hostel.utils.Validation;

@Slf4j
@Component
public class Parser {

	OperationConstants operationConstants = new OperationConstants();

	@Autowired
	Validation validation;

	public List<SearchQuery> getFilters(String filters, String modelName) {
		log.info("parsing the filters: {}", filters);
		List<SearchQuery> searchQueries = new ArrayList<>();
		Arrays
			.stream(filters.split(";"))
			.forEach(filter -> {
				String[] filterParams = filter.split(":");
				String modelValue = filterParams[0];
				String op = filterParams[1];
				String values = filterParams[2];
				validation.validateFilters(modelValue, op, modelName);
				searchQueries.add(
					new SearchQuery(
						modelValue,
						operationConstants.operations.get(op.toUpperCase()),
						values
					)
				);
			});

		return searchQueries;
	}

	public List<Sort.Order> getOrderByFields(String sort, String modelName) {
		List<Sort.Order> sorts = new ArrayList<>();
		Arrays
			.stream(sort.split(";"))
			.forEach(it -> {
				if (it.charAt(0) == '+' || it.charAt(0) == '-') {
					Sort.Direction direction = getSortOperation(it.charAt(0));
					String sortObject = sort.substring(1);
					validation.validateSort(sortObject, modelName);
					sorts.add(new Sort.Order(direction, sortObject));
				} else {
					throw new ParserException(
						"Failed to parse the sort field" + it
					);
				}
			});
		return sorts;
	}

	public Sort.Direction getSortOperation(char operation) {
		if (operation == '+') {
			return Sort.Direction.ASC;
		} else if (operation == '-') {
			return Sort.Direction.DESC;
		}
		return null;
	}
}
