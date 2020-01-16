package hospital.management.Hospital.converter;

import hospital.management.Hospital.dto.DoctorInformationDto;
import hospital.management.Hospital.model.DoctorInformation;
import org.springframework.stereotype.Component;

@Component
public class DoctorInformationConverter {

    public static DoctorInformationDto convertDoctorInfoToDTO(DoctorInformation doctorInformation) {

        return DoctorInformationDto.builder()
                .id(doctorInformation.getId())
                .doctor(doctorInformation.getUser().getId())
                .working_schedule(doctorInformation.getWorkingSchedule())
                .build();
    }
}
