package hospital.management.Hospital.controller;

import hospital.management.Hospital.model.PatientInformation;
import hospital.management.Hospital.service.PatientInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientInformationController {

    @Autowired
    private PatientInformationService patientInformationService;
}
