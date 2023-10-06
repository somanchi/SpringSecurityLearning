package sh.radical.selectallinteger.entities;

import lombok.Data;

@Data
public class PageInfo {

	Long total;

	Integer limit;

	Integer offset;
}
