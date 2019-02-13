package com.example.demo.service;

import com.example.demo.domain.Subject;
import com.example.demo.dto.SubjectDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CycleAvoidingMappingContext;
import com.example.demo.mapper.SubjectMapper;
import com.example.demo.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    private final SubjectMapper subjectMapper;

    private final CycleAvoidingMappingContext cycleAvoidingMappingContext
            = new CycleAvoidingMappingContext();

    @Transactional
    @Override
    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        Subject subject = subjectMapper.toSubject(subjectDTO, cycleAvoidingMappingContext);
        return subjectMapper.fromSubject(subjectRepository.save(subject), cycleAvoidingMappingContext);
    }

    @Transactional
    @Override
    public SubjectDTO getSubject(Long id) {
        Subject subject = subjectRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such subject"));
        return subjectMapper.fromSubject(subject, cycleAvoidingMappingContext);
    }

    @Transactional
    @Override
    public void deleteSubject(Long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            subjectRepository.delete(optionalSubject.get());
        } else {
            throw new ResourceNotFoundException("No such subject!");
        }
    }
}
