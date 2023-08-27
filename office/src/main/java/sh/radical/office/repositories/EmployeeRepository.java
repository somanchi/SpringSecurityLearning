package sh.radical.office.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sh.radical.office.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {}
