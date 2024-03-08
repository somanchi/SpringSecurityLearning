package sh.radical.testingid.utils;

import jakarta.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import sh.radical.testingid.models.Car;

@Component
public class ConvertorDefinition {

	Map<String, Map<String, Field>> classDefinitions = new HashMap<>();

	@PostConstruct
	private void getClassDefinition() {
		classDefinitions.put(
			"car",
			Arrays
				.stream(Car.class.getDeclaredFields())
				.collect(Collectors.toMap(Field::getName, it -> it))
		);
	}
}
