package com.example.demo.dto;

import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude="subjects")
public class StudentDTO {

    private Long id;

    @Size(min = 1, max = 30)
    private String firstName;

    @Size(min = 1, max = 30)
    private String LastName;

    @Past
    private LocalDate birthDate;

    @ToString.Exclude
    private List<GradeDTO> grades = new ArrayList<>();

    @ToString.Exclude
    private Set<SubjectDTO> subjects = new HashSet<>();
}
