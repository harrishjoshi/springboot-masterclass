package com.harxsh.spring.data.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_tutor")
public class Tutor {

    @Id
    @SequenceGenerator(
            name = "tutor_sequence",
            sequenceName = "tutor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tutor_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "tutor_id",
            referencedColumnName = "id"
    )
    private List<Homework> homeworks;
}
