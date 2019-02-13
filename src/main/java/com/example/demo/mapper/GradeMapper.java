package com.example.demo.mapper;

import com.example.demo.domain.Grade;
import com.example.demo.dto.GradeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring", uses = {StudentMapper.class, SubjectMapper.class})
public interface GradeMapper extends EntityMapper<GradeDTO, Grade> {
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "subject.id", target = "subjectId")
    GradeDTO toDto(Grade entity);

    @Mapping(source = "studentId", target = "student")
    @Mapping(source = "subjectId", target = "subject")
    Grade toEntity(GradeDTO dto);

    default Grade fromId(Long id) {
        if (id == null) {
            return null;
        }
        Grade grade = new Grade();
        grade.setId(id);
        return grade;
    }

}
