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
public class DoctorInformationDto implements Serializable {
    private Integer id;
    private Integer doctor;
    private String working_schedule;
}
