package com.example.demo.service;

import com.example.demo.dto.GradeDTO;
import org.springframework.stereotype.Service;


public interface GradeService {

    GradeDTO createGrade(GradeDTO gradeDTO);

    GradeDTO getGrade(Long id);

    void deleteGrade(Long id);

}
