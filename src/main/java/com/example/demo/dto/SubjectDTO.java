package com.example.demo.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude="students")
public class SubjectDTO {

    private Long id;

    @Size(min = 1, max = 30)
    private String name;

  //  @ToString.Exclude
  //  private Set<StudentDTO> students = new HashSet<>();
}
