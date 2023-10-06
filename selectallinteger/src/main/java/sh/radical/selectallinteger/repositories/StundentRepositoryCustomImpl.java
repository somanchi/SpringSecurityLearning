package sh.radical.selectallinteger.repositories;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import sh.radical.selectallinteger.entities.PageInfo;
import sh.radical.selectallinteger.entities.PagedResponse;
import sh.radical.selectallinteger.entities.SearchQuery;
import sh.radical.selectallinteger.exceptions.StundentNotFound;
import sh.radical.selectallinteger.models.Student;
import sh.radical.selectallinteger.utils.Parser;

@Repository
@Slf4j
public class StundentRepositoryCustomImpl implements StundentRepositoryCustom {

	@Autowired
	Parser parser;

	@Value(value = "${default.limit}")
	private Integer defaultLimit;

	@Value(value = "${default.offset}")
	private Integer defaultOffSet;

	@Autowired
	@Lazy
	StundentRepository stundentRepository;

	public PagedResponse<Student> findAllStundents(
		String filters,
		String sort,
		Integer limit,
		Integer offset
	) {
		List<SearchQuery> searchQueries = parser.getFilters(
			filters,
			"stundent"
		);
		List<Sort.Order> sortorder = parser.getOrderByFields(sort, "stundent");
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
			var stundents = stundentRepository.findAll(
				booleanBuilder,
				pageRequest
			);
			var pageResponse = new PagedResponse<Student>();
			pageResponse.setResults(
				stundents.get().collect(Collectors.toList())
			);
			var pageInfo = new PageInfo();
			pageInfo.setTotal(stundents.getTotalElements());
			pageInfo.setLimit(limit);
			pageInfo.setOffset(offset);
			pageResponse.setPageInfo(pageInfo);
			return pageResponse;
		} catch (Exception e) {
			log.error("failed to fetch with filter",e);
			throw new StundentNotFound(
				"failed to fetch students with filters " +
				filters +
				" and order by: " +
				sort
			);
		}
	}
}
