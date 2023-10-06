package sh.radical.selectallinteger.repositories;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import sh.radical.selectallinteger.models.Student;

@Repository
public interface StundentRepository
	extends
		PagingAndSortingRepository<Student, String>,
		CrudRepository<Student, String>,
		QuerydslPredicateExecutor<Student>,
		StundentRepositoryCustom {}
