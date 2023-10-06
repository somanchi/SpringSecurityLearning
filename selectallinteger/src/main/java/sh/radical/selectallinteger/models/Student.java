package sh.radical.selectallinteger.models;

import java.lang.Override;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


@NoArgsConstructor
@Getter
@Setter
@Document
public class Student {

	String name;

	Integer age;

	Instant joiningDate;

	String address;

//	@Field(targetType = FieldType.DECIMAL128)
	BigDecimal score;

	@Id
	String stundentId;

	@Override
	public String toString() {
		return stundentId;
	}

	public static String getNewStundentId() {
		return UUID.randomUUID().toString();
	}
}
