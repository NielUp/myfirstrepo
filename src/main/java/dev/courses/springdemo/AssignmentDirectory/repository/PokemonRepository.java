package dev.courses.springdemo.AssignmentDirectory.repository;

import dev.courses.springdemo.AssignmentDirectory.repository.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {


}
