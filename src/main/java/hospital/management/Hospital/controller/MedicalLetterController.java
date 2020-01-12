package hospital.management.Hospital.controller;

import hospital.management.Hospital.dto.MedicalLetterDto;
import hospital.management.Hospital.service.MedicalLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalLetterController {

    @Autowired
    private MedicalLetterService medicalLetterService;

    @GetMapping("/api/medicalLetter")
    public List<MedicalLetterDto> getAllMedicalLetters(){
        return medicalLetterService.findAllMedicalLetters();
    }

    @GetMapping("/api/medicalLetter/{id}")
    public MedicalLetterDto getMedicalLetterById(@PathVariable("id") Integer id){
        return medicalLetterService.findMedicalLetterById(id);
    }

    @GetMapping("/api/medicalLetter/patient_id={patient_id}")
    public List<MedicalLetterDto> getMedicalLettersOfAPatient(@PathVariable("patient_id") Integer patient_id){
        return medicalLetterService.findMedicalLettersOfAPatient(patient_id);
    }

    @DeleteMapping("/api/medicalLetter/{id}")
    public void deleteMedicalLetterById(@PathVariable("id") Integer id) {
        medicalLetterService.deleteMedicalLetter(id);
    }

    @PostMapping("/api/medicalLetter")
    public void createNewMedicalLetter(@RequestBody MedicalLetterDto medicalLetterDto) {
        medicalLetterService.addMedicalLetter(medicalLetterDto);
    }

    @PutMapping("/api/medicalLetter/{id}")
    public void updateMedicalLetterData(@PathVariable("id") Integer id,@RequestBody MedicalLetterDto medicalLetterDto) {
        medicalLetterService.updateMedicalLetter(id, medicalLetterDto);
    }
}
