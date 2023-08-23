package sh.radical.hostel.controllers;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sh.radical.hostel.entities.Context;
import sh.radical.hostel.models.Room;
import sh.radical.hostel.services.RoomService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/rooms")
public class RoomQueryController {

	@Autowired
	RoomService roomService;

	@GetMapping
	public List<Room> selectAll(
		Context context,
		@RequestParam(value = "filters") String filters,
		@RequestParam(value = "sort") String sort
	) {
		log.info("fetching all values for the filters: {}", filters);
		List<Room> existingrooms = roomService.getAll(context, filters, sort);
		log.info("fetched values matching for filters: {}", filters);
		return existingrooms;
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
