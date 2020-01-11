package hospital.management.Hospital.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name="Department")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Room> rooms;

    private String name;
}
