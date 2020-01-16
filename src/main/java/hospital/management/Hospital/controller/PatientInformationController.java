package hospital.management.Hospital.controller;

import hospital.management.Hospital.dto.PatientInformationDto;
import hospital.management.Hospital.service.PatientInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientInformationController {

    @Autowired
    private PatientInformationService patientInformationService;

    @GetMapping("/api/patients")
    public List<PatientInformationDto> getAllPatients() {
        return patientInformationService.getAllPatients();
    }

    @GetMapping("/api/patients/{id}")
    public PatientInformationDto getPatientById(@PathVariable("id") Integer id) {
        return patientInformationService.getPatient(id);
    }

    @PostMapping("/api/patients")
    public PatientInformationDto createPatient(@RequestBody PatientInformationDto data) {
        return patientInformationService.createPatient(data);
    }

    @PutMapping("/api/patients/{id}")
    public PatientInformationDto updatePatient(@PathVariable("id") Integer id, @RequestBody PatientInformationDto data) {
        return patientInformationService.updatePatient(id, data);
    }

    @DeleteMapping("/api/patients/{id}")
    public boolean deletePatient(@PathVariable("id") Integer id) {
        return patientInformationService.deletePatient(id);
    }
}
