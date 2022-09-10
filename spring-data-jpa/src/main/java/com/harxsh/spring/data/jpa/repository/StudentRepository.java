package com.harxsh.spring.data.jpa.repository;

import com.harxsh.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // JPQL
    @Query("select s from Student s where s.email = ?1")
    Student findStudentByEmail(String email);

    // Native
    @Query(
            value = """
                    select s.* from tbl_student s
                    join tbl_guardian g on g.student_id = s.id
                    where g.email_address = ?1
                    """
            ,
            nativeQuery = true
    )
    Student findStudentByGuardianEmail(String email);

    // Native Named Param
    @Query(
            value = """
                    select s.* from tbl_student s
                    join tbl_guardian g on g.student_id = s.id
                    where g.id = :id
                    """
            ,
            nativeQuery = true
    )
    Student findStudentByGuardianId(@Param("id") Long id);
}
