package sh.radical.hostel.inputs;

import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateRoomInput {

	public String number;

	@ElementCollection
	@CollectionTable(name = "room_names")
	public List<String> names;

	public String numberOfPeople;
}
