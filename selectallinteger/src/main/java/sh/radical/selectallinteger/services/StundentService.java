package sh.radical.selectallinteger.services;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sh.radical.selectallinteger.entities.Context;
import sh.radical.selectallinteger.entities.PagedResponse;
import sh.radical.selectallinteger.exceptions.StundentNotFound;
import sh.radical.selectallinteger.inputs.CreateStundentInput;
import sh.radical.selectallinteger.inputs.UpdateStundentInput;
import sh.radical.selectallinteger.mappers.StundentMapper;
import sh.radical.selectallinteger.models.Student;
import sh.radical.selectallinteger.repositories.StundentRepository;

@Service
@Slf4j
public class StundentService {

	@Autowired
	StundentRepository stundentRepository;

	@Autowired
	StundentMapper stundentMapper;

	public Student create(
		Context context,
		CreateStundentInput createStundentInput,
		String stundentId
	) {
		Student stundent = stundentMapper.createStundent(
			context,
			createStundentInput,
			stundentId
		);
		Student createdStundent = stundentRepository.save(stundent);
		return createdStundent;
	}

	public Student update(
		Context context,
		String stundentId,
		UpdateStundentInput updateStundentInput
	) {
		Optional<Student> existingStundentData = stundentRepository.findById(
			stundentId
		);

		if (existingStundentData.isEmpty()) {
			throw new StundentNotFound();
		}
		Student updatedStundent = stundentMapper.updateStundent(
			context,
			stundentId,
			updateStundentInput,
			existingStundentData.get()
		);
		Student savedStundent = stundentRepository.save(updatedStundent);
		return savedStundent;
	}

	public Student get(Context context, String stundentId) {
		Optional<Student> existingStundentData = stundentRepository.findById(
			stundentId
		);
		if (existingStundentData.isEmpty()) {
			throw new StundentNotFound();
		}
		return existingStundentData.get();
	}

	public PagedResponse<Student> getAll(
		Context context,
		String filters,
		String sort,
		Integer limit,
		Integer offset
	) {
		log.info("inside findAll service method");
		return stundentRepository.findAllStundents(
			filters,
			sort,
			limit,
			offset
		);
	}

	public void delete(Context context, String stundentId) {
		Optional<Student> existingStundentData = stundentRepository.findById(
			stundentId
		);

		if (existingStundentData.isEmpty()) {
			throw new StundentNotFound();
		}
		stundentRepository.deleteById(stundentId);
	}
}
