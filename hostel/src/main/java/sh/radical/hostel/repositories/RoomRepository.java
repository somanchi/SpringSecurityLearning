package sh.radical.hostel.repositories;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sh.radical.hostel.models.Room;
import sh.radical.hostel.repositories.RoomRepositoryCustom;

@Repository
public interface RoomRepository
	extends
		CrudRepository<Room, String>,
		QuerydslPredicateExecutor<Room>,
		RoomRepositoryCustom {}
