package sh.radical.hostel.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sh.radical.hostel.entities.Context;
import sh.radical.hostel.entities.PagedResponse;
import sh.radical.hostel.models.Room;
import sh.radical.hostel.services.RoomService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/rooms")
public class RoomQueryController {

	@Autowired
	RoomService roomService;

	@GetMapping
	public PagedResponse<Room> selectAll(
		Context context,
		@RequestParam(value = "filters") String filters,
		@RequestParam(value = "sort") String sort,
		@RequestParam(value = "limit") Integer limit,
		@RequestParam(value = "offset") Integer offset
	) {
		log.info("fetching values with pagelimit: {}, offset: {} for the filters: {} & sort: {}", limit, offset, filters, sort);
		var rooms = roomService.getAll(context, filters, sort, limit, offset);
		log.info("fetched values matching for filters: {}", filters);
		return rooms;
	}

	@GetMapping(value = "/{roomId}")
	public Room get(
		Context context,
		@PathVariable(value = "roomId") String roomId
	) {
		log.info("Received a get request for Room {} ", roomId);
		Room existingRoom = roomService.get(context, roomId);
		log.info("Get request for Room {} is complete ", roomId);
		return existingRoom;
	}
}
