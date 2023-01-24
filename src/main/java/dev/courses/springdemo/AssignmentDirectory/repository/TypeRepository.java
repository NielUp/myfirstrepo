package dev.courses.springdemo.AssignmentDirectory.repository;

import dev.courses.springdemo.AssignmentDirectory.repository.model.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Long> {
    Optional<TypeEntity> findByName(String name);
}
