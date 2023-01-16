package dev.courses.springdemo.AssignmentDirectory.service;

import dev.courses.springdemo.AssignmentDirectory.controller.error.NotFoundException;
import dev.courses.springdemo.AssignmentDirectory.repository.PokemonRepository;
import dev.courses.springdemo.AssignmentDirectory.repository.model.Pokemon;
import dev.courses.springdemo.AssignmentDirectory.service.dto.PokemonDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public PokemonDto getById(Long id) {
        System.out.println(id + " - getById");
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new NotFoundException("Pokemon not found :("));
        return PokemonDto.builder()
                .id(pokemon.getId())
                .number(pokemon.getNumber())
                .name(pokemon.getName())
                .height(pokemon.getHeight())
                .weight(pokemon.getWeight())
                .build();
    }


    public List<PokemonDto> getAll() {
        System.out.println(" - getAll");
        return pokemonRepository.findAll().stream()
                .map(pokemon -> PokemonDto.builder()
                        .id(pokemon.getId())
                        .number(pokemon.getNumber())
                        .name(pokemon.getName())
                        .height(pokemon.getHeight())
                        .weight(pokemon.getWeight())
                        .build())
                .toList();
    }


    public PokemonDto create(PokemonDto pokemonDto) {
        System.out.println(pokemonDto + " - createPokemon");
        Pokemon savedpokemon = pokemonRepository.save(Pokemon.builder()
                .id(pokemonDto.getId())
                .number(pokemonDto.getNumber())
                .name(pokemonDto.getName())
                .height(pokemonDto.getHeight())
                .weight(pokemonDto.getWeight())
                .build());
        return PokemonDto.builder()
                .id(savedpokemon.getId())
                .number(savedpokemon.getNumber())
                .name(savedpokemon.getName())
                .height(savedpokemon.getHeight())
                .weight(savedpokemon.getWeight())
                .build();
    }


    public PokemonDto updateById(Long id, PokemonDto pokemonDto) {
        System.out.println(pokemonDto + " - updateById");
        Pokemon pokemonToUpdate = pokemonRepository.findById(id).orElseThrow(() -> new NotFoundException("Pokemon not found :("));
        pokemonToUpdate.setNumber(pokemonDto.getNumber());
        pokemonToUpdate.setName(pokemonDto.getName());
        pokemonToUpdate.setWeight(pokemonDto.getWeight());
        pokemonToUpdate.setHeight(pokemonDto.getHeight());
        Pokemon pokemonUpdated = pokemonRepository.save(pokemonToUpdate);
        return PokemonDto.builder()
                .id(pokemonUpdated.getId())
                .number(pokemonUpdated.getNumber())
                .name(pokemonUpdated.getName())
                .height(pokemonUpdated.getHeight())
                .weight(pokemonUpdated.getWeight())
                .build();
    }


    public void deleteById(Long id) {
        System.out.println("deleteById");
        getById(id);
        pokemonRepository.deleteById(id);
    }

}
