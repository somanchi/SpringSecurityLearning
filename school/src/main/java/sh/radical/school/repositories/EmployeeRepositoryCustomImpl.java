package sh.radical.school.repositories;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import sh.radical.school.entities.SearchQuery;
import sh.radical.school.exceptions.EmployeeNotFound;
import sh.radical.school.models.Employee;
import sh.radical.school.repositories.EmployeeRepository;
import sh.radical.school.repositories.EmployeeRepositoryCustom;
import sh.radical.school.utils.Parser;

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

	@Autowired
	Parser parser;

	@Autowired
	@Lazy
	EmployeeRepository employeeRepository;

	public List<Employee> findAllEmployees(String filters, String sort) {
		List<SearchQuery> searchQueries = parser.getFilters(
			filters,
			"employee"
		);
		List<Sort.Order> sortorder = parser.getOrderByFields(sort, "employee");
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
			List<Employee> employees = (List<Employee>) employeeRepository.findAll(
				booleanBuilder,
				Sort.by(sortorder)
			);
			return employees;
		} catch (Exception e) {
			throw new EmployeeNotFound(
				"failed to fetch orders with filters " +
				filters +
				" and order by: " +
				sort
			);
		}
	}
}
