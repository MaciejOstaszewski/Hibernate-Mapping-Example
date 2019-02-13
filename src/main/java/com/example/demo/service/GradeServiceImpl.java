package com.example.demo.service;

import com.example.demo.domain.Grade;
import com.example.demo.dto.GradeDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.GradeMapper;
import com.example.demo.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    private final GradeMapper gradeMapper;

    @Transactional
    @Override
    public GradeDTO createGrade(GradeDTO gradeDTO) {
        Grade grade = gradeMapper.toEntity(gradeDTO);
        return gradeMapper.toDto(gradeRepository.save(grade));
    }

    @Transactional(readOnly = true)
    @Override
    public GradeDTO getGrade(Long id) {
        Grade grade = gradeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No such grade!"));
        return gradeMapper.toDto(grade);
    }

    @Transactional
    @Override
    public void deleteGrade(Long id) {
        Optional<Grade> optionalGrade = gradeRepository.findById(id);
        if (optionalGrade.isPresent()) {
            gradeRepository.deleteById(id);
        } else throw new ResourceNotFoundException("no such grade!");
    }
}
