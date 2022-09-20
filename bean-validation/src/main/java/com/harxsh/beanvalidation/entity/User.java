package com.harxsh.beanvalidation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String mobile;
    private String gender;
    private int age;
    private String country;
}
