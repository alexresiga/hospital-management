package hospital.management.Hospital.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "department", orphanRemoval = true, cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Room> rooms = new HashSet<>();

    private String name;

    public void add_room(Room room)
    {
        room.setDepartment(this);
        this.rooms.add(room);
    }

    @Override
    public String toString()
    {
        return "dep";
    }
}
