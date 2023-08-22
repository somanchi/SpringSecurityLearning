package sh.radical.school.repositories;

import java.util.List;
import sh.radical.school.models.Employee;

public interface EmployeeRepositoryCustom {
	List<Employee> findAllEmployees(String filters, String sort);
}
