package dev.courses.springdemo.AssignmentDirectory.service.mapper;

import dev.courses.springdemo.AssignmentDirectory.gateway.pokeApi.model.PokeApiType;
import dev.courses.springdemo.AssignmentDirectory.repository.model.TypeEntity;
import dev.courses.springdemo.AssignmentDirectory.service.dto.TypeDto;


public class TypeMapper {
    public static TypeDto toDto(TypeEntity typeEntity) {
        return TypeDto.builder()
                .id(typeEntity.getId())
                .name(typeEntity.getName())
                .build();
    }

    public static TypeEntity toEntity(TypeDto dto) {
        return TypeEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public static TypeEntity toEntity(PokeApiType pokeApiType) {
        return TypeEntity.builder()
                .name(pokeApiType.getName())
                .build();
    }

}
