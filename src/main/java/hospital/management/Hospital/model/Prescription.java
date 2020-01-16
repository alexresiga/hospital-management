package hospital.management.Hospital.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "prescriptions")
@EqualsAndHashCode(exclude = {"doctor","patient"})
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private User doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private User patient;

    private String note;

    @ManyToMany
    @JoinTable(
            name = "prescripted_drugs",
            joinColumns = @JoinColumn(name = "prescription_id"),
            inverseJoinColumns = @JoinColumn(name = "drug_id"))
    private Set<Drug> drugs;

    @Override
    public String toString() {
        return "prescription";
    }

}
