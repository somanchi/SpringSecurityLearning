package sh.radical.hostel.models;

import java.lang.Override;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {

	String number;

	@ElementCollection
	@CollectionTable(name = "room_names")
	List<String> names;

	String createdBy;

	String lastModifiedBy;

	Instant createdOn = OffsetDateTime.now().toInstant();

	@Id
	String roomId;

	String numberOfPeople;

	Instant lastModifiedOn = OffsetDateTime.now().toInstant();

	@Override
	public String toString() {
		return roomId;
	}

	public static String getNewRoomId() {
		return UUID.randomUUID().toString();
	}
}
