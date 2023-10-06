package sh.radical.selectallinteger.mappers;

import org.springframework.stereotype.Component;
import sh.radical.selectallinteger.entities.Context;
import sh.radical.selectallinteger.inputs.CreateStundentInput;
import sh.radical.selectallinteger.inputs.UpdateStundentInput;
import sh.radical.selectallinteger.models.Student;

@Component
public class StundentMapper {

	public Student createStundent(
		Context context,
		CreateStundentInput createStundentInput,
		String stundentId
	) {
		Student stundent = new Student();
		stundent.setName(createStundentInput.name);
		stundent.setAge(createStundentInput.age);
		stundent.setJoiningDate(createStundentInput.joiningDate);
		stundent.setAddress(createStundentInput.address);
		stundent.setScore(createStundentInput.score);
		stundent.setStundentId(stundentId);
		return stundent;
	}

	public Student updateStundent(
		Context context,
		String stundentId,
		UpdateStundentInput updateStundentInput,
		Student existingStundent
	) {
		existingStundent.setName(updateStundentInput.name);
		existingStundent.setAge(updateStundentInput.age);
		existingStundent.setJoiningDate(updateStundentInput.joiningDate);
		existingStundent.setAddress(updateStundentInput.address);
		existingStundent.setScore(updateStundentInput.score);
		return existingStundent;
	}
}
