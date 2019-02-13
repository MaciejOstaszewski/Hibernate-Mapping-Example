package com.example.demo.service;

import com.example.demo.domain.Student;
import com.example.demo.dto.StudentDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CycleAvoidingMappingContext;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final CycleAvoidingMappingContext cycleAvoidingMappingContext
            = new CycleAvoidingMappingContext();

    @Transactional
    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentMapper.toStudent(studentDTO, cycleAvoidingMappingContext);
        return studentMapper.fromStudent(studentRepository.save(student), cycleAvoidingMappingContext);
    }

    @Transactional(readOnly = true)
    @Override
    public StudentDTO getStudent(Long id) {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such student!"));
        System.out.println(student);
        return studentMapper.fromStudent(student, cycleAvoidingMappingContext);
    }

    @Transactional
    @Override
    public void deleteStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No such student!");
        }
    }
}
