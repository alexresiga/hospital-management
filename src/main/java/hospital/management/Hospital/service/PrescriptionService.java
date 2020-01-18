package hospital.management.Hospital.service;


import hospital.management.Hospital.converter.PrescriptionConverter;
import hospital.management.Hospital.dto.PrescriptionDto;
import hospital.management.Hospital.exceptions.ExceptionClass;
import hospital.management.Hospital.exceptions.NotFoundException;
import hospital.management.Hospital.model.Drug;
import hospital.management.Hospital.model.Prescription;
import hospital.management.Hospital.model.User;
import hospital.management.Hospital.repository.DrugRepository;
import hospital.management.Hospital.repository.PrescriptionRepository;
import hospital.management.Hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<PrescriptionDto> findAllPrescriptions() {
        return prescriptionRepository.findAll().stream()
                .map(presc -> prescriptionRepository.getDrugsFromPrescription(presc.getId()))
                .map(PrescriptionConverter::convertPrescriptionToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PrescriptionDto> findPrescriptionsOfAPatient(Integer patient_id){
        return prescriptionRepository.findAll().stream()
                                    .filter(prescription -> prescription.getPatient().getId().equals(patient_id))
                                    .map(PrescriptionConverter::convertPrescriptionToDto)
                                    .collect(Collectors.toList());
    }

    @Transactional
    public PrescriptionDto findPrescriptionById(Integer id){
        return PrescriptionConverter.convertPrescriptionToDto(prescriptionRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Transactional
    public boolean deletePrescriptionById(Integer id){
        PrescriptionDto prescription_to_delete = findPrescriptionById(id);
        if(prescription_to_delete == null){
            throw new ExceptionClass("Prescription does not exist!");
        }
        prescriptionRepository.deleteById(id);
        return true;
    }

    @Transactional
    public PrescriptionDto updatePrescription(Integer id, PrescriptionDto prescriptionDto){
        Prescription prescription_to_update = prescriptionRepository.findById(id).orElse(null);
        if(prescription_to_update == null){
            throw new ExceptionClass("Prescription does not exist!");
        }
        if(prescriptionDto.getDoctor()!=null) {
            User doctor = userRepository.findById(prescriptionDto.getDoctor()).orElse(null);
            prescription_to_update.setDoctor(doctor == null ? prescription_to_update.getDoctor() : doctor);
        }
        if(prescriptionDto.getPatient()!=null) {
            User patient = userRepository.findById(prescriptionDto.getPatient()).orElse(null);
            prescription_to_update.setPatient(patient == null ? prescription_to_update.getPatient() : patient);
        }
        Set<Drug> drugs = new HashSet<>();
        if (prescriptionDto.getDrugs() != null) {
            drugs = prescriptionDto.getDrugs().stream()
                    .map(drug_id -> drugRepository.findById(drug_id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
        }
        prescription_to_update.setDrugs(drugs.size() != 0 ? drugs : prescription_to_update.getDrugs());
        return PrescriptionConverter.convertPrescriptionToDto(prescriptionRepository.save(prescription_to_update));
    }

    @Transactional
    public PrescriptionDto addPrescription(PrescriptionDto prescriptionDto)
    {
        User doctor = userRepository.findById(prescriptionDto.getDoctor()).orElse(null);
        User patient = userRepository.findById(prescriptionDto.getPatient()).orElse(null);
        if(patient == null || doctor == null ){
            throw new ExceptionClass("Invalid data for the prescription!");
        }
        Set<Drug> drugs = new HashSet<>();
        if (prescriptionDto.getDrugs() != null) {
            drugs = prescriptionDto.getDrugs().stream()
                    .map(drug_id -> drugRepository.findById(drug_id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
        }
        Prescription new_prescription = new Prescription(null,doctor,patient,prescriptionDto.getNote(),drugs);
        return PrescriptionConverter.convertPrescriptionToDto(prescriptionRepository.save(new_prescription));
    }
}
