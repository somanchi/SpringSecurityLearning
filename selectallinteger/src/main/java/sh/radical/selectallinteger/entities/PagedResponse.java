package sh.radical.selectallinteger.entities;

import java.util.List;
import lombok.Data;

@Data
public class PagedResponse<T> {

	public PagedResponse(List<T> results, PageInfo pageInfo) {
		this.results = results;
		this.pageInfo = pageInfo;
	}

	public PagedResponse() {
	}
	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	List<T> results;

	PageInfo pageInfo;
}
