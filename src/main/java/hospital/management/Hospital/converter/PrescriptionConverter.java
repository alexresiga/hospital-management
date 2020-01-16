package hospital.management.Hospital.converter;


import hospital.management.Hospital.dto.PrescriptionDto;
import hospital.management.Hospital.model.Drug;
import hospital.management.Hospital.model.Prescription;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PrescriptionConverter {

    public static PrescriptionDto convertPrescriptionToDto(Prescription prescription)
    {
        return PrescriptionDto.builder()
                .id(prescription.getId())
                .doctor(prescription.getDoctor().getId())
                .patient(prescription.getPatient().getId())
                .note(prescription.getNote())
                .drugs(prescription.getDrugs().stream().map(Drug::getId).collect(Collectors.toList()))
                .build();
    }
}
