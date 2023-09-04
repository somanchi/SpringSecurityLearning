package sh.radical.hostel.repositories;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import sh.radical.hostel.entities.PageInfo;
import sh.radical.hostel.entities.PagedResponse;
import sh.radical.hostel.entities.SearchQuery;
import sh.radical.hostel.exceptions.RoomNotFound;
import sh.radical.hostel.models.Room;
import sh.radical.hostel.utils.Parser;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoomRepositoryCustomImpl implements RoomRepositoryCustom {

	@Autowired
	Parser parser;

	@Autowired
	@Lazy
	RoomRepository roomRepository;

	public PagedResponse<Room> findAllRooms(String filters, String sort, Integer limit, Integer offset ) {
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
			var pageRequest = PageRequest.of(offset, limit, Sort.by(sortorder));
			var rooms =  roomRepository.findAll( booleanBuilder, pageRequest );
			var pageResponse  = new PagedResponse<Room>();
			pageResponse.setResults(rooms.get().collect(Collectors.toList()));
			var pageInfo = new PageInfo();
			pageInfo.setTotal(rooms.getTotalElements());
			pageInfo.setLimit(limit);
			pageInfo.setOffset(offset);
			pageResponse.setPageInfo(pageInfo);
			return pageResponse;
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
