package vn.itz.jpastudying.Dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentPagination<T> {

  private List<T> content;
  private long totalElements;
  private int totalPages;
}
