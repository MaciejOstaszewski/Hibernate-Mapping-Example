package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "subjects"})
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude="subjects")
@Entity
@Table(name = "students")
@NamedEntityGraph(name = "graph.Student.grades",
                    attributeNodes = @NamedAttributeNode(value = "grades"))
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "student", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Grade> grades;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_subjects",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
    private Set<Subject> subjects = new HashSet<>();;

    public void addSubject(Subject s) {
        this.subjects.add(s);
        s.getStudents().add(this);
    }

    public void removeSubject(Subject s) {
        this.subjects.remove(s);
        s.getStudents().remove(this);
    }

    public void addGrade(Grade g) {
        this.grades.add(g);
        g.setStudent(this);
    }

}
