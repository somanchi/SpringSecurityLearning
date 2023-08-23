package sh.radical.hostel.repositories;

import java.util.List;
import sh.radical.hostel.models.Room;

public interface RoomRepositoryCustom {
	List<Room> findAllRooms(String filters, String sort);
}
