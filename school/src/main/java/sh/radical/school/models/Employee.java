package sh.radical.school.models;

import java.lang.Override;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	String employeeId;

	String name;

	String age;

	String gender;

	@Override
	public String toString() {
		return employeeId;
	}

	public static String getNewEmployeeId() {
		return UUID.randomUUID().toString();
	}
}
