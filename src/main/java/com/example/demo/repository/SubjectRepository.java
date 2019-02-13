package com.example.demo.repository;

import com.example.demo.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("DELETE FROM Subject s WHERE s.id = ?1")
    void delete(@Param("id") Long id);
}
