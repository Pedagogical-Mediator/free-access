package com.example.freeaccess.repository;

import com.example.freeaccess.domain.student_union.StudentUnion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentUnionRepository extends JpaRepository<StudentUnion, Integer> {
}
