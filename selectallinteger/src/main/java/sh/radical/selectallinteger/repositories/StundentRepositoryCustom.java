package sh.radical.selectallinteger.repositories;

import sh.radical.selectallinteger.entities.PagedResponse;
import sh.radical.selectallinteger.models.Student;

public interface StundentRepositoryCustom {
	PagedResponse<Student> findAllStundents(
		String filters,
		String sort,
		Integer limit,
		Integer offset
	);
}
