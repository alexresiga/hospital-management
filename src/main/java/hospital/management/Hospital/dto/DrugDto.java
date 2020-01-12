package hospital.management.Hospital.dto;

import hospital.management.Hospital.model.Prescription;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
@Builder
public class DrugDto implements Serializable {
    private Integer id;
    private String name;
    private List<Integer> prescriptions;
}
