package hospital.management.Hospital.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
@Builder
public class PatientInformationDto implements Serializable {
    private Integer id;
    private Integer patient;
    private String fields;
}
