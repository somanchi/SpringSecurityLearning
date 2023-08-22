package sh.radical.school.repositories;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sh.radical.school.models.Employee;
import sh.radical.school.repositories.EmployeeRepositoryCustom;

@Repository
public interface EmployeeRepository
	extends
		CrudRepository<Employee, String>,
		QuerydslPredicateExecutor<Employee>,
		EmployeeRepositoryCustom {}
