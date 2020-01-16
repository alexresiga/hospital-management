package hospital.management.Hospital.model;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "doctor_information")
public class DoctorInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", unique = true)
    private User user;

    private String workingSchedule;

    public String toString()
    {
        return "doctor";
    }
}
