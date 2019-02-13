package com.example.demo.service;

import com.example.demo.dto.StudentDTO;
import org.springframework.stereotype.Service;


public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO getStudent(Long id);

    void deleteStudent(Long id);
}
