package com.example.demo;

import com.example.demo.domain.Student;
import com.example.demo.domain.Subject;
import com.example.demo.dto.GradeDTO;
import com.example.demo.mapper.GradeMapper;
import com.example.demo.repository.GradeRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@FixMethodOrder(MethodSorters.JVM)
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class GradeResourceIntTest {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper json;


    private GradeDTO createGrade() {
        GradeDTO grade = new GradeDTO();
        grade.setId(1L);
        grade.setValue(2);
        return grade;
    }

    private Student createStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Name");
        student.setLastName("Name");
        student.setBirthDate(LocalDate.now().minus(1, ChronoUnit.DAYS));
        student.setGrades(new ArrayList<>());
        student.setSubjects(new HashSet<>());
        return student;
    }

    private Subject createSubject() {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Name");
        subject.setStudents(new HashSet<>());
        return subject;
    }

    @Test
    public void shouldCreateGrade() throws Exception {

        studentRepository.saveAndFlush(createStudent());
        subjectRepository.saveAndFlush(createSubject());
        GradeDTO grade = createGrade();
        grade.setSubjectId(createSubject().getId());
        grade.setStudentId(createStudent().getId());

        mockMvc.perform(post("/api/grades")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json.writeValueAsString(grade)))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.value").value(2))
                .andExpect(jsonPath("$.studentId").exists())
                .andExpect(jsonPath("$.subjectId").exists())
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    public void shouldGetGrade() throws Exception {
        studentRepository.saveAndFlush(createStudent());
        subjectRepository.saveAndFlush(createSubject());
        GradeDTO grade = createGrade();
        grade.setSubjectId(createSubject().getId());
        grade.setStudentId(createStudent().getId());
        gradeRepository.saveAndFlush(gradeMapper.toEntity(grade));

        mockMvc.perform(get("/api/grades/{id}", grade.getId()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.value").value(2))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void shouldDeleteGrade() throws Exception {
        studentRepository.saveAndFlush(createStudent());
        subjectRepository.saveAndFlush(createSubject());
        GradeDTO gradeDTO = createGrade();
        gradeRepository.saveAndFlush(gradeMapper.toEntity(gradeDTO));

        mockMvc.perform(delete("/api/grades/{id}", gradeDTO.getId()))
                .andExpect(status().isOk()).andDo(print());
        mockMvc.perform(get("/api/grades/{id}", gradeDTO.getId()))
                .andExpect(status().isNotFound()).andDo(print());
    }
}
