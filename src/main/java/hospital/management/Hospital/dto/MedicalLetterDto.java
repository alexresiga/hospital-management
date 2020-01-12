package hospital.management.Hospital.dto;

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
public class MedicalLetterDto implements Serializable {
    private Integer id;
    private Integer doctor;
    private Integer patient;
    private String description;
}
