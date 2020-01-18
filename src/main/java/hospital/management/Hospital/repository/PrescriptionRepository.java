package hospital.management.Hospital.repository;

import hospital.management.Hospital.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

    @Query(value = "select * from PRESCRIPTIONS inner join PRESCRIPTED_DRUGS  on PRESCRIPTIONS.id = PRESCRIPTED_DRUGS.prescription_id  inner join DRUGS on PRESCRIPTED_DRUGS.drug_id = DRUGS.id where prescriptions.id = ?1 "
            , nativeQuery = true)
    Prescription getDrugsFromPrescription(Integer id);
}
