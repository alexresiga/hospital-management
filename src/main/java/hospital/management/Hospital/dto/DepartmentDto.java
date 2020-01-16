package hospital.management.Hospital.dto;

import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
@Builder
public class DepartmentDto implements Serializable {
    private Integer id;
    private String name;
    private List<Integer> rooms;
}
