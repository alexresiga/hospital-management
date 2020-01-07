package hospital.management.Hospital.repository;

import hospital.management.Hospital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}
