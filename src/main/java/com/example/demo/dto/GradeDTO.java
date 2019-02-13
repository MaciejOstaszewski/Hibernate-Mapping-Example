package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
public class GradeDTO implements Serializable {

    private Long id;

    @Positive
    @Max(5)
    private Integer value;

    private Long studentId;

    private Long subjectId;

//    private StudentDTO studentDTO;
//
//    private SubjectDTO subjectDTO;
}
