package hospital.management.Hospital.repository;

import hospital.management.Hospital.model.DoctorInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorInformationRepository extends JpaRepository<DoctorInformation, Integer> {
}
