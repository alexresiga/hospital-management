package hospital.management.Hospital.converter;

import hospital.management.Hospital.dto.DrugDto;
import hospital.management.Hospital.model.Drug;
import hospital.management.Hospital.model.Prescription;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DrugConverter {

    public static DrugDto convertDrugToDto(Drug drug)
    {
        return DrugDto.builder()
                .id(drug.getId())
                .name(drug.getName())
                .prescriptions(drug.getPrescriptions().stream().map(Prescription::getId).collect(Collectors.toList()))
                .build();
    }
}
