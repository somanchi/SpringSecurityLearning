package sh.radical.testingid.services;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sh.radical.testingid.entities.Context;
import sh.radical.testingid.entities.PagedResponse;
import sh.radical.testingid.exceptions.CarNotFound;
import sh.radical.testingid.inputs.CreateCarInput;
import sh.radical.testingid.inputs.UpdateCarInput;
import sh.radical.testingid.mappers.CarMapper;
import sh.radical.testingid.models.Car;
import sh.radical.testingid.repositories.CarRepository;

@Service
@Slf4j
public class CarService {

	@Autowired
	CarRepository carRepository;

	@Autowired
	CarMapper carMapper;

	public PagedResponse<Car> getAll(
		Context context,
		String filters,
		String sort,
		Integer limit,
		Integer offset
	) {
		log.info("inside findAll service method");
		return carRepository.findAllCars(filters, sort, limit, offset);
	}

	public void delete(Context context, String ID) {
		Optional<Car> existingCarData = carRepository.findById(ID);

		if (existingCarData.isEmpty()) {
			throw new CarNotFound();
		}
		carRepository.deleteById(ID);
	}

	public Car update(
		Context context,
		String ID,
		UpdateCarInput updateCarInput
	) {
		Optional<Car> existingCarData = carRepository.findById(ID);

		if (existingCarData.isEmpty()) {
			throw new CarNotFound();
		}
		Car updatedCar = carMapper.updateCar(
			context,
			ID,
			updateCarInput,
			existingCarData.get()
		);
		Car savedCar = carRepository.save(updatedCar);
		return savedCar;
	}

	public Car create(
		Context context,
		CreateCarInput createCarInput,
		String ID
	) {
		Car car = carMapper.createCar(context, createCarInput, ID);
		Car createdCar = carRepository.save(car);
		return createdCar;
	}

	public Car get(Context context, String ID) {
		Optional<Car> existingCarData = carRepository.findById(ID);
		if (existingCarData.isEmpty()) {
			throw new CarNotFound();
		}
		return existingCarData.get();
	}
}
