package hospital.management.Hospital.repository;

import hospital.management.Hospital.model.MedicalLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalLettersRepository extends JpaRepository<MedicalLetter, Integer> {
}
