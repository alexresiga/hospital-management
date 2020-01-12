package hospital.management.Hospital.service;

import hospital.management.Hospital.converter.DrugConverter;
import hospital.management.Hospital.dto.DrugDto;
import hospital.management.Hospital.exceptions.ExceptionClass;
import hospital.management.Hospital.exceptions.NotFoundException;
import hospital.management.Hospital.model.Drug;
import hospital.management.Hospital.model.Prescription;
import hospital.management.Hospital.repository.DrugRepository;
import hospital.management.Hospital.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DrugService {
    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public DrugDto findDrugById(Integer id){
        return DrugConverter.convertDrugToDto(drugRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    public List<DrugDto> findAllDrugs(){
        return drugRepository.findAll().stream()
                .map(DrugConverter::convertDrugToDto)
                .collect(Collectors.toList());
    }

    public void deleteDrug(Integer id){
        DrugDto drug_to_delete = findDrugById(id);
        if(drug_to_delete == null){
            throw new ExceptionClass("Drug does not exist!");
        }
        drugRepository.deleteById(id);
    }

    public void updateDrug(Integer id, DrugDto drugDto) {
        Drug drug_to_update = drugRepository.findById(id).orElse(null);
        if(drug_to_update == null){
            throw new ExceptionClass("Drug does not exist!");
        }
        drug_to_update.setName(drugDto.getName() == null ? drug_to_update.getName() : drugDto.getName());
        Set<Prescription> prescriptions = drugDto.getPrescriptions()
                                            .stream()
                                            .map(prescription_id -> prescriptionRepository.findById(prescription_id).orElse(null))
                                            .filter(Objects::nonNull)
                                            .collect(Collectors.toSet());
        drug_to_update.setPrescriptions(prescriptions.size() == 0 ? drug_to_update.getPrescriptions() : prescriptions);
        drugRepository.save(drug_to_update);
    }

    public void addDrug(DrugDto drugDto){
        Set<Prescription> prescriptions = drugDto.getPrescriptions().stream()
                                            .map(prescription_id -> prescriptionRepository.findById(prescription_id).orElse(null))
                                            .filter(Objects::nonNull)
                                            .collect(Collectors.toSet());
        Drug new_drug = new Drug(null,drugDto.getName(),prescriptions);
        drugRepository.save(new_drug);
    }
}
