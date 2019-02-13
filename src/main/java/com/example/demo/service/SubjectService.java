package com.example.demo.service;

import com.example.demo.dto.SubjectDTO;

public interface SubjectService {

    SubjectDTO createSubject(SubjectDTO subjectDTO);

    SubjectDTO getSubject(Long id);

    void deleteSubject(Long id);

}
