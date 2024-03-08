package sh.radical.testingid.entities;

import com.querydsl.core.types.Operator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchQuery {

	private String filterName;

	private Operator operation;

	private List<Object> filterValue;
}
