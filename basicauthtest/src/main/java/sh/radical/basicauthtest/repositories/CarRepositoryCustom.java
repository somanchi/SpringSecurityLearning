package sh.radical.basicauthtest.repositories;

import sh.radical.basicauthtest.entities.PagedResponse;
import sh.radical.basicauthtest.models.Car;

public interface CarRepositoryCustom {
	PagedResponse<Car> findAllCars(
		String filters,
		String sort,
		Integer limit,
		Integer offset
	);
}
