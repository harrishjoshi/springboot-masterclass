package com.harxsh.spring.data.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_homework")
@ToString(exclude = "tutor")
public class Homework {

    @Id
    @SequenceGenerator(
            name = "homework_sequence",
            sequenceName = "homework_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "homework_sequence"
    )
    private Long id;
    private String title;
    private Integer creditHours;
    @OneToOne(mappedBy = "homework")
    private HomeworkResource homeworkResource;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "tutor_id",
            referencedColumnName = "id"
    )
    private Tutor tutor;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_student_homework",
            joinColumns = @JoinColumn(
                    name = "homework_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "id"
            )
    )
    private List<Student> students;

    public void addStudents(Student student) {
        if (students == null) students = new ArrayList<>();

        students.add(student);
    }
}
