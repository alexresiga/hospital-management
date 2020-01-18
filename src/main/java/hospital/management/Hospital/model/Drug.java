package hospital.management.Hospital.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "drugs")
@EqualsAndHashCode(exclude = {"prescriptions"})
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "drugs", fetch = FetchType.LAZY)
    private Set<Prescription> prescriptions;
}
