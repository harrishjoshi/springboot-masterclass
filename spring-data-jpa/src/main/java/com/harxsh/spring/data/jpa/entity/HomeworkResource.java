package com.harxsh.spring.data.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_homework_resource")
@ToString(exclude = "homework")
public class HomeworkResource {

    @Id
    @SequenceGenerator(
            name = "homework_resource_sequence",
            sequenceName = "homework_resource_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "homework_resource_sequence"
    )
    private Long id;
    private String content;
    private String externalUrl;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "homework_id",
            referencedColumnName = "id"
    )
    private Homework homework;
}
