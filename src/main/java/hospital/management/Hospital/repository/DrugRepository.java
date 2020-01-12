package hospital.management.Hospital.repository;

import hospital.management.Hospital.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DrugRepository extends JpaRepository<Drug, Integer> {

    @Query(value = "select D from DRUGS D inner join PRESCRIPTED_DRUGS PD on D.id = PD.drug_id\n"+
                    " inner join PRESCRIPTIONS P on PD.prescription_id = P.id\n"+
                    " where D.id = :id", nativeQuery = true)
    Drug getDrugsPrescriptions(@Param("id") Integer id);
}
