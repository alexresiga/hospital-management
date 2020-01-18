package hospital.management.Hospital.dto;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
@Builder
public class AppointmentDto implements Serializable {
    private Integer id;
    private Integer doctor;
    private Integer patient;
    private LocalDate date;
    private String approved;
    private Integer room;
}
