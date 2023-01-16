package dev.courses.springdemo.AssignmentDirectory.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PokemonDto {
    private Long id;
    private Integer number;
    private String name;
    private Integer height;
    private Integer weight;

}
