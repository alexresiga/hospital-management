package hospital.management.Hospital.model;


import lombok.*;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "users")
public class User {

    @Id
    private String username;

    private String password;

    private String full_name;

    @Column(unique = true)
    private String email;

    private String phone_number;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
