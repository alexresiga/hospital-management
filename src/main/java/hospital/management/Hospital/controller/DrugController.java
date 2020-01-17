package hospital.management.Hospital.controller;


import hospital.management.Hospital.dto.DrugDto;
import hospital.management.Hospital.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("api/drugList")
    public List<DrugDto> findAllDrugs()
    {
        return drugService.findAllDrugs();
    }

    @PostMapping("/api/drug")
    public DrugDto addDrug(@RequestBody DrugDto drugDto) {
        return drugService.addDrug(drugDto);
    }

    @DeleteMapping("/api/drug/{id}")
    public boolean deleteDrug(@PathVariable("id") Integer id) {
        return drugService.deleteDrug(id);
    }

    @PutMapping("/api/drug/{id}")
    public DrugDto updateDrug(@PathVariable("id") int id, @RequestBody DrugDto drugDto) {
        return drugService.updateDrug(id, drugDto);
    }

}
