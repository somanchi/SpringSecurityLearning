package sh.radical.hostel.repositories;

import sh.radical.hostel.entities.PagedResponse;
import sh.radical.hostel.models.Room;

public interface RoomRepositoryCustom {
	PagedResponse<Room> findAllRooms(String filters, String sort, Integer limit, Integer offset);
}
