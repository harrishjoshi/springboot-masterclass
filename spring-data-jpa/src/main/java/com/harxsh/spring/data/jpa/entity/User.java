package com.harxsh.spring.data.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "tbl_user",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_email",
                columnNames = "email"
        )
)
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String email;

    @Embedded
    @AttributeOverride(name = "temp", column = @Column(name = "temp_address"))
    @AttributeOverride(name = "perm", column = @Column(name = "perm_address"))
    Address address;
}
