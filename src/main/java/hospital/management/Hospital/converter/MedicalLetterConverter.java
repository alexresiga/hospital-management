package hospital.management.Hospital.converter;

import hospital.management.Hospital.dto.MedicalLetterDto;
import hospital.management.Hospital.model.MedicalLetter;
import org.springframework.stereotype.Component;

@Component
public class MedicalLetterConverter {

    public static MedicalLetterDto convertMedicalLetterToDTO(MedicalLetter medicalLetter) {

        return MedicalLetterDto.builder()
                .id(medicalLetter.getId())
                .description(medicalLetter.getDescription())
                .doctor(medicalLetter.getDoctor().getId())
                .patient(medicalLetter.getPatient().getId())
                .build();
    }
}
