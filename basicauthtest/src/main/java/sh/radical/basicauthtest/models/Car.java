package sh.radical.basicauthtest.models;

import java.lang.Override;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import sh.radical.basicauthtest.models.Award;
import sh.radical.basicauthtest.models.Owner;

@NoArgsConstructor
@Getter
@Setter
@Document
public class Car {

	@Id
	String carId;

	String name;

	Owner owner;

	String vehicleType;

	List<Award> awards;

	@Override
	public String toString() {
		return carId;
	}

	public static String getNewCarId() {
		return UUID.randomUUID().toString();
	}
}
