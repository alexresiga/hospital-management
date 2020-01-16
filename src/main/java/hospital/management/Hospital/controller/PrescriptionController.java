package hospital.management.Hospital.controller;

import hospital.management.Hospital.dto.PrescriptionDto;
import hospital.management.Hospital.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/api/prescription/{id}")
    public PrescriptionDto findPrescriptionById(@PathVariable("id") Integer id)
    {
        return prescriptionService.findPrescriptionById(id);
    }

    @GetMapping("/api/prescription/{patient_id}")
    public List<PrescriptionDto> findPrescriptionsOfaPatient(@PathVariable("patient_id") Integer id)
    {
        return prescriptionService.findPrescriptionsOfAPatient(id);
    }

    @GetMapping("/api/prescription")
    public List<PrescriptionDto> findAllPrescriptions()
    {
        return prescriptionService.findAllPrescriptions();
    }

}
