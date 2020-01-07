package hospital.management.Hospital.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "roles")
public class Role{
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    private String name;

}
