package sh.radical.selectallinteger.entities;

import com.querydsl.core.types.Operator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchQuery {

	String filterName;

	Operator operation;

	Object filterValue;
}
