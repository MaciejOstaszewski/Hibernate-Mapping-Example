package com.example.demo.mapper;

import com.example.demo.domain.Subject;
import com.example.demo.dto.SubjectDTO;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, GradeMapper.class})
public interface SubjectMapper {

    Subject toSubject(SubjectDTO dto, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration(name = "toSubject")
    SubjectDTO fromSubject(Subject entity, @Context CycleAvoidingMappingContext context);

    default Subject fromId(Long id) {
        if (id == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(id);
        return subject;
    }
}
