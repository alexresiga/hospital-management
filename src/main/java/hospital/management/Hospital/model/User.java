package hospital.management.Hospital.model;


import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
@EqualsAndHashCode(exclude = {"doctor_information", "patient_information"})
public class User {

    public User(String password, String full_name, String email, Role role) {
        this.password = password;
        this.full_name = full_name;
        this.email = email;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String password;

    private String full_name;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private DoctorInformation doctor_information;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PatientInformation patient_information;

    @Override
    public String toString() {
        return "user";
    }

}
