package sh.radical.testingid.mappers;

import org.springframework.stereotype.Component;
import sh.radical.testingid.entities.Context;
import sh.radical.testingid.inputs.CreateCarInput;
import sh.radical.testingid.inputs.UpdateCarInput;
import sh.radical.testingid.models.Car;

@Component
public class CarMapper {

	public Car updateCar(
		Context context,
		String ID,
		UpdateCarInput updateCarInput,
		Car existingCar
	) {
		existingCar.setName(updateCarInput.getName());
		existingCar.setOwner(updateCarInput.getOwner());
		existingCar.setVehicleType(updateCarInput.getVehicleType());
		existingCar.setAwards(updateCarInput.getAwards());
		return existingCar;
	}

	public Car createCar(
		Context context,
		CreateCarInput createCarInput,
		String ID
	) {
		Car car = new Car();
		car.setName(createCarInput.getName());
		car.setOwner(createCarInput.getOwner());
		car.setVehicleType(createCarInput.getVehicleType());
		car.setAwards(createCarInput.getAwards());
		car.setID(ID);
		return car;
	}
}
