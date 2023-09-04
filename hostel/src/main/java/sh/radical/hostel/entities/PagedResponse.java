package sh.radical.hostel.entities;

import lombok.Data;

import java.util.List;

@Data
public class PagedResponse<T> {
    List<T> results;
    PageInfo pageInfo;
}
