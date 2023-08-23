package sh.radical.hostel.mappers;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Component;
import sh.radical.hostel.entities.Context;
import sh.radical.hostel.inputs.CreateRoomInput;
import sh.radical.hostel.inputs.UpdateRoomInput;
import sh.radical.hostel.models.Room;

@Component
public class RoomMapper {

	public Room updateRoom(
		Context context,
		String roomId,
		UpdateRoomInput updateRoomInput,
		Room existingRoom
	) {
		existingRoom.setNumber(updateRoomInput.number);
		existingRoom.setNames(updateRoomInput.names);
		existingRoom.setCreatedBy(context.userContext.userId);
		existingRoom.setLastModifiedBy(context.userContext.userId);
		existingRoom.setCreatedOn(OffsetDateTime.now().toInstant());
		existingRoom.setNumberOfPeople(updateRoomInput.numberOfPeople);
		existingRoom.setLastModifiedOn(OffsetDateTime.now().toInstant());
		return existingRoom;
	}

	public Room createRoom(
		Context context,
		CreateRoomInput createRoomInput,
		String roomId
	) {
		Room room = new Room();
		room.setNumber(createRoomInput.number);
		room.setNames(createRoomInput.names);
		room.setCreatedBy(context.userContext.userId);
		room.setLastModifiedBy(context.userContext.userId);
		room.setCreatedOn(OffsetDateTime.now().toInstant());
		room.setNumberOfPeople(createRoomInput.numberOfPeople);
		room.setLastModifiedOn(OffsetDateTime.now().toInstant());
		room.setRoomId(roomId);
		return room;
	}
}
