package sh.radical.basicauthtest.mappers;

import org.springframework.stereotype.Component;
import sh.radical.basicauthtest.entities.Context;
import sh.radical.basicauthtest.inputs.CreateCarInput;
import sh.radical.basicauthtest.inputs.UpdateCarInput;
import sh.radical.basicauthtest.models.Car;

@Component
public class CarMapper {

	public Car createCar(
		Context context,
		CreateCarInput createCarInput,
		String carId
	) {
		Car car = new Car();
		car.setName(createCarInput.name);
		car.setOwner(createCarInput.owner);
		car.setVehicleType(createCarInput.vehicleType);
		car.setAwards(createCarInput.awards);
		car.setCarId(carId);
		return car;
	}

	public Car updateCar(
		Context context,
		String carId,
		UpdateCarInput updateCarInput,
		Car existingCar
	) {
		existingCar.setName(updateCarInput.name);
		existingCar.setOwner(updateCarInput.owner);
		existingCar.setVehicleType(updateCarInput.vehicleType);
		existingCar.setAwards(updateCarInput.awards);
		return existingCar;
	}
}
