package hospital.management.Hospital.controller;


import hospital.management.Hospital.dto.DrugDto;
import hospital.management.Hospital.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DrugController {
    @Autowired
    private DrugService drugService;

    @GetMapping("/api/drug/{id}")
    public DrugDto getDrugById(@PathVariable("id") Integer id)
    {
        return drugService.findDrugById(id);
    }

    @GetMapping("api/drug")
    public List<DrugDto> findAllDrugs()
    {
        return drugService.findAllDrugs();
    }

}
