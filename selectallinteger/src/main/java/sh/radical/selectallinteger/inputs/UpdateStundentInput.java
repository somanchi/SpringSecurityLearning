package sh.radical.selectallinteger.inputs;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateStundentInput {

	public String name;

	public Integer age;

	public Instant joiningDate;

	public String address;

	public BigDecimal score;
}
