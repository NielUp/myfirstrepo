package dev.courses.springdemo.AssignmentDirectory.controller;

import dev.courses.springdemo.AssignmentDirectory.service.PokemonService;
import dev.courses.springdemo.AssignmentDirectory.service.dto.PokemonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "pokemon")
public class PokemonController {
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("{id}")
    public PokemonDto getById(@PathVariable Long id) {
        return pokemonService.getById(id);
    }

    @GetMapping
    public List<PokemonDto> getAll() {

        return pokemonService.getAll();
    }

    @PostMapping
    public PokemonDto create(@RequestBody PokemonDto pokemonDto) {

        return pokemonService.create(pokemonDto);
    }

    @PutMapping("{id}")
    public PokemonDto updateById(@PathVariable Long id, @RequestBody PokemonDto pokemonDto) {

        return pokemonService.updateById(id, pokemonDto);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        pokemonService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
