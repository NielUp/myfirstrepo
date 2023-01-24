package dev.courses.springdemo.AssignmentDirectory.controller;

import dev.courses.springdemo.AssignmentDirectory.service.TypeService;
import dev.courses.springdemo.AssignmentDirectory.service.dto.PokemonDto;
import dev.courses.springdemo.AssignmentDirectory.service.dto.TypeDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(value = "types")
public class TypeController {
    private final TypeService typeService;

    @PostMapping("poke-api")
    @Operation(description = "Get all types from Poke API and save it to DB")
    public List<TypeDto> fetchAllFromApi() {
        return typeService.fetchAndSaveAllFromApi();
    }

    @GetMapping
    @Operation(description = "Get all types from DB")
    public List<TypeDto> getAll() {
        return typeService.getAll();
    }
}
