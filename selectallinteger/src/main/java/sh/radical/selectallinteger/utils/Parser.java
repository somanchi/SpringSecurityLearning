package sh.radical.selectallinteger.utils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.converters.models.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import sh.radical.selectallinteger.entities.SearchQuery;
import sh.radical.selectallinteger.exceptions.ParserException;


@Slf4j
@Component
public class Parser {

	private final Pattern FILTER_SYNTAX_PATTERN = Pattern.compile("([\\w]+):([\\w]+):(.*)");

	OperationConstants operationConstants = new OperationConstants();

	@Autowired
	Validation validation;


	@Autowired
	ConvertorDefinition convertorDefinition;

	public List<SearchQuery> getFilters(String filters, String modelName) {
		if (filters == null || filters.isEmpty()) {
			return new ArrayList<>();
		} else {
			log.info("parsing the filters: {}", filters);
			List<SearchQuery> searchQueries = new ArrayList<>();
			Arrays
				.stream(filters.split(":and:"))
				.forEach(filter -> {
					if (! FILTER_SYNTAX_PATTERN.matcher(filter).matches()) {
						throw new ParserException("invalid syntax for filter " + filter);
					}
					Matcher matcher = FILTER_SYNTAX_PATTERN.matcher(filter);
					matcher.find();
					String modelValue = matcher.group(1);
					String op = matcher.group(2);
					Object values = typeCastValue(modelValue,matcher.group(3),modelName);
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
	}

	private Object typeCastValue (String modelValue,String value, String modelName) {
		var classDetails = convertorDefinition.classDefinitions.get(modelName);
		if (! classDetails.containsKey(modelValue)) {
			throw new ParserException("Key not found");
		}
		else {
			var type = classDetails.get(modelValue).getType().getSimpleName();
			Object typedValue = null;
			switch (type) {
				case "Integer" -> typedValue = Integer.valueOf(value);
				case "boolean" -> typedValue = Boolean.valueOf(value);
				case "BigDecimal" -> typedValue = new BigDecimal(value);
				case "Instant" -> typedValue =Instant.parse(value);
				default -> typedValue = value.toString();
			}
			return  typedValue;
		}
	}

	public List<Sort.Order> getOrderByFields(String sort, String modelName) {
		if (sort == null || sort.isEmpty()) {
			return new ArrayList<>();
		} else {
			List<Sort.Order> sorts = new ArrayList<>();
			AtomicReference<Sort.Direction> direction = new AtomicReference<>(Sort.Direction.ASC);
			Arrays
				.stream(sort.split(";"))
				.forEach(it -> {
					if (it.charAt(0) == '+' || it.charAt(0) == '-') {
						direction.set(getSortOperation(
								it.charAt(0)
						));
					}
					String sortObject = sort.substring(1);
					validation.validateSort(sortObject, modelName);
					sorts.add(new Sort.Order(direction.get(), sortObject));
				});
			return sorts;
		}
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
