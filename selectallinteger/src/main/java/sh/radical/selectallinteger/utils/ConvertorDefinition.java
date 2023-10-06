package sh.radical.selectallinteger.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import sh.radical.selectallinteger.models.Student;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ConvertorDefinition {
    Map<String, Map<String , Field>> classDefinitions = new HashMap<>();

    @PostConstruct
    public void getClassDefinition() {
        classDefinitions.put("stundent", Arrays.stream(Student.class.getDeclaredFields()).collect(Collectors.toMap(Field::getName, it -> it) ));
    }
}
