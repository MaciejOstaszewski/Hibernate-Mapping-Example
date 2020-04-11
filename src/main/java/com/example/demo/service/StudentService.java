package com.example.demo.service;

import com.example.demo.dto.StudentDTO;

import java.util.List;


public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO getStudent(Long id);

    List<StudentDTO> getStudentList();

    void deleteStudent(Long id);
}
