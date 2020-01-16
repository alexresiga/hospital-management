package hospital.management.Hospital.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Room")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "rooms")
@EqualsAndHashCode(exclude="department")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id", referencedColumnName = "id")
    private Department department;

    private String name;
    private String level;

    @Override
    public String toString()
    {
        return "room";
    }
}
