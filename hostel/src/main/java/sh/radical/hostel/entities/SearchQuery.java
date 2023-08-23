package sh.radical.hostel.entities;

import com.querydsl.core.types.Operator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchQuery {

	String queryObject;

	Operator operation;

	String value;
}
