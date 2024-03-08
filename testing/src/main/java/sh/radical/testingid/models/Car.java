package sh.radical.testingid.models;

import jakarta.validation.Valid;
import java.lang.Override;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import sh.radical.testingid.models.Award;
import sh.radical.testingid.models.Owner;

@NoArgsConstructor
@Getter
@Setter
@Document
public class Car {

	private String name;

	@Valid
	private Owner owner;

	@Id
	private String ID;

	private String vehicleType;

	private List<String> awards;

	@Override
	public String toString() {
		return ID;
	}

	public static String getNewID() {
		return UUID.randomUUID().toString();
	}
}
