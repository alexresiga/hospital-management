package hospital.management.Hospital.model;


import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "username", updatable = false, nullable = false)
    private String username;

    private String password;

    private String full_name;

    @UniqueElements
    private String email;

    private String phone_number;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
