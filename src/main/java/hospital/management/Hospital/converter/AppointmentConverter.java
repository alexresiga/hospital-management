package hospital.management.Hospital.converter;


import hospital.management.Hospital.dto.AppointmentDto;
import hospital.management.Hospital.model.Appointment;
import hospital.management.Hospital.service.DepartmentService;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConverter {

    public static AppointmentDto convertAppointmentToDto(Appointment appointment)
    {
        return AppointmentDto.builder()
                .id(appointment.getId())
                .doctor(appointment.getDoctor().getId())
                .patient(appointment.getPatient().getId())
                .date(appointment.getDate())
                .approved(appointment.getApproved())
                .room(appointment.getRoom().getId())
                .build();
    }
}
