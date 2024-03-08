package sh.radical.testingid.repositories;

import sh.radical.testingid.entities.PagedResponse;
import sh.radical.testingid.models.Car;

public interface CarRepositoryCustom {
	PagedResponse<Car> findAllCars(
		String filters,
		String sort,
		Integer limit,
		Integer offset
	);
}
