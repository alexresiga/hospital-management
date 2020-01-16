package hospital.management.Hospital.controller;

import hospital.management.Hospital.dto.DoctorInformationDto;
import hospital.management.Hospital.service.DoctorInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorInformationController {

    @Autowired
    private DoctorInformationService doctorInformationService;

    @GetMapping("/api/doctors")
    public List<DoctorInformationDto> getAllDoctors() {
        return doctorInformationService.getAllDoctors();
    }

    @GetMapping("/api/doctors/{id}")
    public DoctorInformationDto getDoctorById(@PathVariable("id") Integer id) {
        return doctorInformationService.getDoctor(id);
    }

    @PostMapping("/api/doctors")
    public DoctorInformationDto createDoctor(@RequestBody DoctorInformationDto data) {
        return doctorInformationService.createDoctor(data);
    }

    @PutMapping("/api/doctors/{id}")
    public DoctorInformationDto updateDoctor(@PathVariable("id") Integer id, @RequestBody DoctorInformationDto data) {
        return doctorInformationService.updateDoctor(id, data);
    }

    @DeleteMapping("/api/doctors/{id}")
    public boolean deleteDoctor(@PathVariable("id") Integer id) {
        return doctorInformationService.deleteDoctor(id);
    }

}
