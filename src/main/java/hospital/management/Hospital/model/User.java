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

    public User(String username, String password, String full_name, String email, String phone_number, Role role) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String full_name;

    @Column(unique = true)
    private String email;

    private String phone_number;

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
