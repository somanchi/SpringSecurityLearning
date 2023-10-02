package sh.radical.basicauthtest.inputs;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sh.radical.basicauthtest.models.Award;
import sh.radical.basicauthtest.models.Owner;

@NoArgsConstructor
@Getter
public class UpdateCarInput {

	public String name;

	public Owner owner;

	public String vehicleType;

	public List<Award> awards;
}
