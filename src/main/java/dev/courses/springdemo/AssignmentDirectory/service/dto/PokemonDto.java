package dev.courses.springdemo.AssignmentDirectory.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PokemonDto {
    private Long id;
    private Integer number;

    //@NotEmpty
    //@Max(20)
    private String name;

    //@NotNull
    //@Max(1000000)
    private Integer height;
    private Integer weight;

    private List<TypeDto> types;

}
