package hospital.management.Hospital.converter;

import hospital.management.Hospital.dto.PatientInformationDto;
import hospital.management.Hospital.model.PatientInformation;
import org.springframework.stereotype.Component;

@Component
public class PatientInformationConverter {

    public static PatientInformationDto convertPatientInformationToDTO(PatientInformation patientInformation) {
        return PatientInformationDto.builder()
                .id(patientInformation.getId())
                .patient(patientInformation.getUser().getId())
                .fields(patientInformation.getFields())
                .build();
    }
}
