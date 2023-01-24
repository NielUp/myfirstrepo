package dev.courses.springdemo.AssignmentDirectory.service;

import dev.courses.springdemo.AssignmentDirectory.gateway.pokeApi.PokeApiGateway;
import dev.courses.springdemo.AssignmentDirectory.gateway.pokeApi.model.PokeApiType;
import dev.courses.springdemo.AssignmentDirectory.repository.TypeRepository;
import dev.courses.springdemo.AssignmentDirectory.repository.model.TypeEntity;
import dev.courses.springdemo.AssignmentDirectory.service.dto.TypeDto;
import dev.courses.springdemo.AssignmentDirectory.service.mapper.TypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TypeService {
    private final TypeRepository typeRepository;
    private final PokeApiGateway pokeApiGateway;

    public TypeService(TypeRepository typeRepository, PokeApiGateway pokeApiGateway) {
        this.typeRepository = typeRepository;
        this.pokeApiGateway = pokeApiGateway;
    }

    public TypeDto createTypeIfNotExists(String name) {
        return TypeMapper.toDto(createTypeEntityIfNotExists(name));
    }

    public TypeEntity createTypeEntityIfNotExists(String name) {
        Optional<TypeEntity> typeEntityOptional = typeRepository.findByName(name); // check if type already exists in DB
        // if exists, just return it
        // if not exists, save it and return saved entity
        return typeEntityOptional.orElseGet(() -> typeRepository.save(new TypeEntity(name)));
    }

    public List<TypeDto> fetchAndSaveAllFromApi() {
        List<PokeApiType> pokeApiTypes = pokeApiGateway.getAllTypes().getResults();

        // For each fetched type from API, check if it's already existing in DB.
        // If not, save it by calling "createTypeIfNotExists"
        return pokeApiTypes.stream()
                .map(pokeApiType -> createTypeIfNotExists(pokeApiType.getName()))
                .toList();
    }

    public List<TypeDto> getAll() {
        return typeRepository.findAll().stream()
                .map(TypeMapper::toDto)
                .toList();
    }

}
