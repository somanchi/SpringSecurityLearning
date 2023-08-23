package sh.radical.hostel.repositories;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import sh.radical.hostel.entities.SearchQuery;
import sh.radical.hostel.exceptions.RoomNotFound;
import sh.radical.hostel.models.Room;
import sh.radical.hostel.repositories.RoomRepository;
import sh.radical.hostel.repositories.RoomRepositoryCustom;
import sh.radical.hostel.utils.Parser;

@Repository
public class RoomRepositoryCustomImpl implements RoomRepositoryCustom {

	@Autowired
	Parser parser;

	@Autowired
	@Lazy
	RoomRepository roomRepository;

	public List<Room> findAllRooms(String filters, String sort) {
		List<SearchQuery> searchQueries = parser.getFilters(filters, "room");
		List<Sort.Order> sortorder = parser.getOrderByFields(sort, "room");
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		for (SearchQuery query : searchQueries) {
			booleanBuilder.and(
				Expressions.predicate(
					query.getOperation(),
					Expressions.stringPath(query.getQueryObject()),
					Expressions.constant(query.getValue())
				)
			);
		}
		try {
			List<Room> rooms = (List<Room>) roomRepository.findAll(
				booleanBuilder,
				Sort.by(sortorder)
			);
			return rooms;
		} catch (Exception e) {
			throw new RoomNotFound(
				"failed to fetch orders with filters " +
				filters +
				" and order by: " +
				sort
			);
		}
	}
}
