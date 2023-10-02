package sh.radical.basicauthtest.entities;

import java.util.List;
import lombok.Data;

@Data
public class PagedResponse<T> {

	List<T> results;

	PageInfo pageInfo;
}
