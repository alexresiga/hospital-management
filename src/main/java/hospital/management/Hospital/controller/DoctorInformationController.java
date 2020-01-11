package hospital.management.Hospital.controller;

import hospital.management.Hospital.service.DoctorInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorInformationController {

    @Autowired
    private DoctorInformationService doctorInformationService;
}
