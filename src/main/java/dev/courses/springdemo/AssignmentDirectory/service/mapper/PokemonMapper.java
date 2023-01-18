package dev.courses.springdemo.AssignmentDirectory.service.mapper;

import dev.courses.springdemo.AssignmentDirectory.gateway.pokeApi.model.PokeApiPokemon;
import dev.courses.springdemo.AssignmentDirectory.repository.model.PokemonEntity;
import dev.courses.springdemo.AssignmentDirectory.service.dto.PokemonDto;


public class PokemonMapper {
    public static PokemonDto toDTO(PokemonEntity pokemonEntity){
        return PokemonDto.builder()
                .id(pokemonEntity.getId())
                .number(pokemonEntity.getNumber())
                .name(pokemonEntity.getName())
                .height(pokemonEntity.getHeight())
                .weight(pokemonEntity.getWeight())
                .build();
    }

    public static PokemonEntity toEntity(PokemonDto dto ){
        return PokemonEntity.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .name(dto.getName())
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .build();
    }

    public static PokemonEntity toEntity(PokeApiPokemon pokeApiPokemon){
        return PokemonEntity.builder()
                .number(pokeApiPokemon.getNumber())
                .name(pokeApiPokemon.getName())
                .height(pokeApiPokemon.getHeight())
                .weight(pokeApiPokemon.getWeight())
                .build();
    }

}
