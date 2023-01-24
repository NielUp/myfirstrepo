package dev.courses.springdemo.AssignmentDirectory.controller;

import dev.courses.springdemo.AssignmentDirectory.service.PokemonService;
import dev.courses.springdemo.AssignmentDirectory.service.dto.PokemonDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(value = "pokemon")
public class PokemonController {
    private final PokemonService pokemonService;


    @GetMapping("{id}")
    @Operation(description = "Get pokemon by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Pokemon not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public PokemonDto getById(@PathVariable
                              @Parameter(description = "Existing pokemon ID") Long id) {
        return pokemonService.getById(id);
    }

    @GetMapping
    public List<PokemonDto> getAll() {

        return pokemonService.getAll();
    }

    @PostMapping
    public PokemonDto create(@Valid @RequestBody PokemonDto pokemonDto) {

        return pokemonService.create(pokemonDto);
    }

    @PostMapping("poke-api")
    @Operation(description = "Save pokemon + types by name from Poke API")
    public PokemonDto createFromApi(
            @PathVariable
            @RequestParam
                    String name) {


        return pokemonService.createFromApi(name);
    }

    @PutMapping("{id}")
    public PokemonDto updateById(@PathVariable Long id, @Valid @RequestBody PokemonDto pokemonDto) {

        return pokemonService.updateById(id, pokemonDto);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        pokemonService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
