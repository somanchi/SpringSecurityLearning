package sh.radical.testingid.entities;

import java.util.List;
import lombok.Data;

@Data
public class PagedResponse<T> {

	private List<T> results;

	private PageInfo pageInfo;
}
