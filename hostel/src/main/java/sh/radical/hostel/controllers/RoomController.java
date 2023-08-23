package sh.radical.hostel.controllers;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.radical.hostel.entities.Context;
import sh.radical.hostel.inputs.CreateRoomInput;
import sh.radical.hostel.inputs.TestRoomInput;
import sh.radical.hostel.inputs.UpdateRoomInput;
import sh.radical.hostel.models.Room;
import sh.radical.hostel.services.RoomService;

@Slf4j
@RestController
@RequestMapping(value = "/v1/rooms")
public class RoomController {

	@Autowired
	RoomService roomService;

	@DeleteMapping(value = "/{roomId}")
	public void delete(
		Context context,
		@PathVariable(value = "roomId") String roomId
	) {
		log.info("Received a delete request for Room {} ", roomId);
		roomService.delete(context, roomId);
		log.info("Delete request completed for Room {} ", roomId);
	}

	@PutMapping(value = "/{roomId}")
	public ResponseEntity update(
		Context context,
		@PathVariable(value = "roomId") String roomId,
		@Valid @RequestBody UpdateRoomInput updateRoomInput
	) {
		log.info("Received a update request for Room {} ", roomId);
		roomService.update(context, roomId, updateRoomInput);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/v1/rooms/" + roomId);
		log.info("Update request for Room {} is complete", roomId);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity create(
		Context context,
		@Valid @RequestBody CreateRoomInput createRoomInput
	) {
		log.info("Received a new create request");
		var id = Room.getNewRoomId();
		roomService.create(context, createRoomInput, id);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", "/v1/rooms/" + id);
		log.info("Create request for Room - {} is complete", id);
		return new ResponseEntity(null, responseHeaders, HttpStatus.OK);
	}
}
