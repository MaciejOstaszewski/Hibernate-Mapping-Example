package com.example.demo.mapper;

import com.example.demo.domain.Student;
import com.example.demo.dto.StudentDTO;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SubjectMapper.class, GradeMapper.class})
public interface StudentMapper {

    Student toStudent(StudentDTO dto, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration(name = "toStudent")
    StudentDTO fromStudent(Student entity, @Context CycleAvoidingMappingContext context);

    default Student fromId(Long id) {
        if (id == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        return student;
    }
}
