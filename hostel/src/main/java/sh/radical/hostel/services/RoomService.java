package sh.radical.hostel.services;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sh.radical.hostel.entities.Context;
import sh.radical.hostel.entities.PagedResponse;
import sh.radical.hostel.exceptions.RoomNotFound;
import sh.radical.hostel.inputs.CreateRoomInput;
import sh.radical.hostel.inputs.TestRoomInput;
import sh.radical.hostel.inputs.UpdateRoomInput;
import sh.radical.hostel.mappers.RoomMapper;
import sh.radical.hostel.models.Room;
import sh.radical.hostel.repositories.RoomRepository;

@Service
@Slf4j
public class RoomService {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	RoomMapper roomMapper;

	public PagedResponse<Room> getAll(Context context, String filters, String sort, Integer limit, Integer offset) {
		log.info("inside findAll service method");
		return roomRepository.findAllRooms(filters, sort,limit,offset);
	}

	public void delete(Context context, String roomId) {
		Optional<Room> existingRoomData = roomRepository.findById(roomId);

		if (existingRoomData.isEmpty()) {
			throw new RoomNotFound();
		}
		roomRepository.deleteById(roomId);
	}

	public Room update(
		Context context,
		String roomId,
		UpdateRoomInput updateRoomInput
	) {
		Optional<Room> existingRoomData = roomRepository.findById(roomId);

		if (existingRoomData.isEmpty()) {
			throw new RoomNotFound();
		}
		Room updatedRoom = roomMapper.updateRoom(
			context,
			roomId,
			updateRoomInput,
			existingRoomData.get()
		);
		Room savedRoom = roomRepository.save(updatedRoom);
		return savedRoom;
	}

	public void Test(
		Context context,
		TestRoomInput testRoomInput,
		String roomId
	) {
		//TODO
	}

	public Room create(
		Context context,
		CreateRoomInput createRoomInput,
		String roomId
	) {
		Room room = roomMapper.createRoom(context, createRoomInput, roomId);
		Room createdRoom = roomRepository.save(room);
		return createdRoom;
	}

	public Room get(Context context, String roomId) {
		Optional<Room> existingRoomData = roomRepository.findById(roomId);
		if (existingRoomData.isEmpty()) {
			throw new RoomNotFound();
		}
		return existingRoomData.get();
	}
}
