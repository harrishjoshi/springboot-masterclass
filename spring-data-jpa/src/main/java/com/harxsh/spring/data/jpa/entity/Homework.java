package com.harxsh.spring.data.jpa.entity;

import lombok.*;

import javax.persistence.*;

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
}
