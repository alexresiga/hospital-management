package hospital.management.Hospital.repository;

import hospital.management.Hospital.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

    @Query(value = "select P from PRESCRIPTIONS P inner join PRESCRIPTED_DRUGS PD on P.id = PD.prescription_id\n"+
            " inner join DRUGS D on PD.drug_id = D.id\n"+
            " where P.id = :id", nativeQuery = true)
        Prescription getDrugsFromPrescription(@Param("id") Integer id);
}
