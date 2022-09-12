package com.harxsh.spring.data.jpa.repository;

import com.harxsh.spring.data.jpa.entity.HomeworkResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkResourceRepository extends JpaRepository<HomeworkResource, Long> {
}
