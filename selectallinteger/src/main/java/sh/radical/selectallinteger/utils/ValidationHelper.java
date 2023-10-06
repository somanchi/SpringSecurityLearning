package sh.radical.selectallinteger.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class ValidationHelper {

	Map<String, Map<String, List<String>>> allowedFiltersForField = new HashMap<>();

	Map<String, Set<String>> allowedSort = new HashMap<>();


	public ValidationHelper() {



		// adding sorts to set
		allowedSort.put(
				"stundent",
				Set.of(
						"name",
						"age",
						"joiningDate",
						"address",
						"score",
						"stundentId"
				)
		);
		// adding allowed sorts to the map
		allowedFiltersForField.put(
				"stundent",
				Map.of(
						"name",
						List.of("eq"),
						"age",
						List.of("eq", "gt", "gte", "lt", "lte"),
						"joiningDate",
						List.of("gt", "gte", "lt", "lte"),
						"address",
						List.of("eq"),
						"score",
						List.of("eq", "gt", "gte", "lt", "lte"),
						"stundentId",
						List.of("eq")
				)
		);
	}

}