package dev.courses.springdemo.AssignmentDirectory.service;

import dev.courses.springdemo.AssignmentDirectory.service.dto.PokemonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class PokemonService {

    public PokemonDto getById(Long id) {
        System.out.println(id + " - getById");
        return null;
    }


    public List<PokemonDto> getAll() {
        System.out.println(" - getAll");
        return null;
    }


    public PokemonDto create(PokemonDto pokemonDto) {
        System.out.println(pokemonDto + " - createPokemon");
        return null;
    }


    public PokemonDto updateById(Long id, PokemonDto pokemonDto) {
        System.out.println(pokemonDto + " - updateById");
        return null;
    }


    public void deleteById(Long id) {
        System.out.println("deleteById");
    }

}
