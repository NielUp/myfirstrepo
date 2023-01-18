package dev.courses.springdemo.AssignmentDirectory.gateway.pokeApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokeApiPokemon {
    @JsonProperty(value = "id")
    private Integer number;
    @JsonProperty(value = "base_experience")
    private Integer baseExperience;
    private String name;
    private Integer height;
    private Integer weight;
    private List<PokeApiTypes> types;
}
