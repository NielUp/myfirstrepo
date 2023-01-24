package dev.courses.springdemo.AssignmentDirectory.gateway.pokeApi;

import dev.courses.springdemo.AssignmentDirectory.controller.error.BadGatewayException;
import dev.courses.springdemo.AssignmentDirectory.gateway.pokeApi.model.PokeApiPokemon;
import dev.courses.springdemo.AssignmentDirectory.gateway.pokeApi.model.PokeApiResultTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PokeApiGateway {

    @Value("${application.poke-api.url}")
    private String pokeApiUrl;

    public PokeApiPokemon getPokemonByName(String name) {
        try {
            return new RestTemplateBuilder().build().getForObject(
                    pokeApiUrl + "pokemon/{name}",
                    PokeApiPokemon.class,
                    Map.of("name", name)
            );
        } catch (
                HttpClientErrorException ex) {
            throw new BadGatewayException("Error when calling Star Wars API", ex);
        }
    }

    public PokeApiResultTypes getAllTypes() {
        try {
            return new RestTemplateBuilder().build().getForObject(
                    pokeApiUrl + "type",
                    PokeApiResultTypes.class
            );
        } catch (
                HttpClientErrorException ex) {
            throw new BadGatewayException("Error when calling Star Wars API", ex);
        }
    }

}
