package sh.radical.testingid.inputs;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sh.radical.testingid.models.Award;
import sh.radical.testingid.models.Owner;

@NoArgsConstructor
@Getter
@Setter
public class CreateCarInput {

	private String name;

	private Owner owner;

	private String vehicleType;

	private List<String> awards;
}
