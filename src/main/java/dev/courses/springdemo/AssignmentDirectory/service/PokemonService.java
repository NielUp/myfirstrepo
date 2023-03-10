package dev.courses.springdemo.AssignmentDirectory.service;

import dev.courses.springdemo.AssignmentDirectory.controller.error.NotFoundException;
import dev.courses.springdemo.AssignmentDirectory.gateway.pokeApi.PokeApiGateway;
import dev.courses.springdemo.AssignmentDirectory.gateway.pokeApi.model.PokeApiPokemon;
import dev.courses.springdemo.AssignmentDirectory.repository.PokemonRepository;
import dev.courses.springdemo.AssignmentDirectory.repository.model.PokemonEntity;
import dev.courses.springdemo.AssignmentDirectory.service.dto.PokemonDto;
import dev.courses.springdemo.AssignmentDirectory.service.mapper.PokemonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.courses.springdemo.AssignmentDirectory.service.mapper.PokemonMapper.toDTO;
import static dev.courses.springdemo.AssignmentDirectory.service.mapper.PokemonMapper.toEntity;

@Slf4j
@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;
    private final PokeApiGateway pokeApiGateway;

    public PokemonService(PokemonRepository pokemonRepository, PokeApiGateway pokeApiGateway) {
        this.pokemonRepository = pokemonRepository;
        this.pokeApiGateway = pokeApiGateway;
    }

    public PokemonDto getById(Long id) {
        System.out.println(id + " - getById");
        PokemonEntity pokemon = pokemonRepository.findById(id).orElseThrow(() -> new NotFoundException("Pokemon not found :("));

        log.info("beep boop lol its done did it");



        return toDTO(pokemon);
    }


    public List<PokemonDto> getAll() {
        System.out.println(" - getAll");
        return pokemonRepository.findAll().stream()
                .map(PokemonMapper::toDTO)
                .toList();
    }


    public PokemonDto create(PokemonDto pokemonDto) {
        System.out.println(pokemonDto + " - createPokemon");
        PokemonEntity savedpokemon = pokemonRepository.save(toEntity(pokemonDto));
        return toDTO(savedpokemon);
    }


    public PokemonDto updateById(Long id, PokemonDto pokemonDto) {
        System.out.println(pokemonDto + " - updateById");
        PokemonEntity pokemonToUpdate = pokemonRepository.findById(id).orElseThrow(() -> new NotFoundException("Pokemon not found :("));
        pokemonToUpdate.setNumber(pokemonDto.getNumber());
        pokemonToUpdate.setName(pokemonDto.getName());
        pokemonToUpdate.setWeight(pokemonDto.getWeight());
        pokemonToUpdate.setHeight(pokemonDto.getHeight());
        PokemonEntity pokemonUpdated = pokemonRepository.save(pokemonToUpdate);
        return toDTO(pokemonUpdated);
    }


    public void deleteById(Long id) {
        System.out.println("deleteById");
        getById(id);
        pokemonRepository.deleteById(id);
    }


    public PokemonDto createFromApi(String name) {
        //call api
        PokeApiPokemon pokeApiPokemon = pokeApiGateway.getPokemonByName(name);
        //model api to PokeEntity
        PokemonEntity pokemonEntity = toEntity(pokeApiPokemon);
        //save PokeEntity
        PokemonEntity savedPokemonEntity = pokemonRepository.save(pokemonEntity);
        //return DTO
        return toDTO(savedPokemonEntity);
    }
}
