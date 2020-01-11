package hospital.management.Hospital.repository;

import hospital.management.Hospital.model.PatientInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientInformationRepository extends JpaRepository<PatientInformation, Integer> {
}
