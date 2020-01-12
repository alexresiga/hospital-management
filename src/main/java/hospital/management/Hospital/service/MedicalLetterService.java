package hospital.management.Hospital.service;

import hospital.management.Hospital.converter.MedicalLetterConverter;
import hospital.management.Hospital.dto.MedicalLetterDto;
import hospital.management.Hospital.exceptions.ExceptionClass;
import hospital.management.Hospital.exceptions.NotFoundException;
import hospital.management.Hospital.model.MedicalLetter;
import hospital.management.Hospital.model.User;
import hospital.management.Hospital.repository.MedicalLettersRepository;
import hospital.management.Hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalLetterService {

    @Autowired
    private MedicalLettersRepository medicalLettersRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<MedicalLetterDto> findAllMedicalLetters() {
        return medicalLettersRepository.findAll().stream()
                                .map(MedicalLetterConverter::convertMedicalLetterToDTO)
                                .collect(Collectors.toList());
    }

    @Transactional
    public List<MedicalLetterDto> findMedicalLettersOfAPatient(Integer patient_id) {
        return medicalLettersRepository.findAll().stream()
                                .filter(medicalLetter -> medicalLetter.getPatient().getId().equals(patient_id))
                                .map(MedicalLetterConverter::convertMedicalLetterToDTO)
                                .collect(Collectors.toList());
    }

    @Transactional
    public MedicalLetterDto findMedicalLetterById(Integer id) {
        return MedicalLetterConverter.convertMedicalLetterToDTO(medicalLettersRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Transactional
    public void deleteMedicalLetter(Integer id){
        MedicalLetter medicalLetter_to_delete = medicalLettersRepository.findById(id).orElse(null);
        if(medicalLetter_to_delete == null){
            throw new ExceptionClass("Medical letter does not exist!");
        }
        medicalLettersRepository.deleteById(id);
    }

    @Transactional
    public void updateMedicalLetter(Integer id, MedicalLetterDto medicalLetterDto) {
        MedicalLetter medicalLetter_to_update = medicalLettersRepository.findById(id).orElse(null);
        if(medicalLetter_to_update == null){
            throw new ExceptionClass("Medical letter does not exist!");
        }
        if(medicalLetterDto.getDoctor()!=null) {
            User doctor = userRepository.findById(medicalLetterDto.getDoctor()).orElse(null);
            medicalLetter_to_update.setDoctor(doctor == null ? medicalLetter_to_update.getDoctor() : doctor);
        }
        if(medicalLetterDto.getPatient()!=null) {
            User patient = userRepository.findById(medicalLetterDto.getPatient()).orElse(null);
            medicalLetter_to_update.setPatient(patient == null ? medicalLetter_to_update.getPatient() : patient);
        }
        medicalLetter_to_update.setDescription(medicalLetterDto.getDescription() == null ? medicalLetter_to_update.getDescription() : medicalLetterDto.getDescription());

        medicalLettersRepository.save(medicalLetter_to_update);
    }

    @Transactional
    public void addMedicalLetter(MedicalLetterDto medicalLetterDto){
        User doctor = userRepository.findById(medicalLetterDto.getDoctor()).orElse(null);
        User patient = userRepository.findById(medicalLetterDto.getPatient()).orElse(null);
        MedicalLetter new_medicalLetter = new MedicalLetter(null,doctor,patient,medicalLetterDto.getDescription());
        medicalLettersRepository.save(new_medicalLetter);
    }

}
