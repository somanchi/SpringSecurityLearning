package sh.radical.basicauthtest.repositories;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import sh.radical.basicauthtest.entities.PageInfo;
import sh.radical.basicauthtest.entities.PagedResponse;
import sh.radical.basicauthtest.entities.SearchQuery;
import sh.radical.basicauthtest.exceptions.CarNotFound;
import sh.radical.basicauthtest.models.Car;
import sh.radical.basicauthtest.repositories.CarRepository;
import sh.radical.basicauthtest.repositories.CarRepositoryCustom;
import sh.radical.basicauthtest.utils.Parser;

@Repository
public class CarRepositoryCustomImpl implements CarRepositoryCustom {

	@Autowired
	Parser parser;

	@Value(value = "${default.limit}")
	private Integer defaultLimit;

	@Value(value = "${default.offset}")
	private Integer defaultOffSet;

	@Autowired
	@Lazy
	CarRepository carRepository;

	public PagedResponse<Car> findAllCars(
		String filters,
		String sort,
		Integer limit,
		Integer offset
	) {
		List<SearchQuery> searchQueries = parser.getFilters(filters, "car");
		List<Sort.Order> sortorder = parser.getOrderByFields(sort, "car");
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
			if (limit == null) {
				limit = defaultLimit;
			}
			if (offset == null) {
				offset = defaultOffSet;
			}
			var pageRequest = PageRequest.of(offset, limit, Sort.by(sortorder));
			var cars = carRepository.findAll(booleanBuilder, pageRequest);
			var pageResponse = new PagedResponse<Car>();
			pageResponse.setResults(cars.get().collect(Collectors.toList()));
			var pageInfo = new PageInfo();
			pageInfo.setTotal(cars.getTotalElements());
			pageInfo.setLimit(limit);
			pageInfo.setOffset(offset);
			pageResponse.setPageInfo(pageInfo);
			return pageResponse;
		} catch (Exception e) {
			throw new CarNotFound(
				"failed to fetch orders with filters " +
				filters +
				" and order by: " +
				sort
			);
		}
	}
}
