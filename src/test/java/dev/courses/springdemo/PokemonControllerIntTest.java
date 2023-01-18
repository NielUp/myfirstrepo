package dev.courses.springdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import dev.courses.springdemo.AssignmentDirectory.gateway.pokeApi.PokeApiGateway;
import dev.courses.springdemo.AssignmentDirectory.gateway.pokeApi.model.PokeApiPokemon;
import dev.courses.springdemo.AssignmentDirectory.repository.PokemonRepository;
import dev.courses.springdemo.AssignmentDirectory.repository.model.PokemonEntity;
import dev.courses.springdemo.AssignmentDirectory.service.dto.PokemonDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.UnsupportedEncodingException;

import static com.fasterxml.jackson.databind.cfg.ConstructorDetector.USE_PROPERTIES_BASED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
@EnableWebMvc
@ComponentScan("dev.courses.springdemo")
class PokemonControllerIntTest {

    public static final int NUMBER = 150;
    public static final int HEIGHT = 25;
    public static final int WEIGHT = 80;
    public static final String NAME = "mewtwo";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PokemonRepository pokemonRepository;
    @MockBean
    private PokeApiGateway pokeApiGateway;

    @AfterEach
    public void clean() {
        pokemonRepository.deleteAll();


    }



    @Test
    void createPokemon_withGoodData_shouldReturnSavedPokemon() throws Exception {
        // init test
        int usersBefore = pokemonRepository.findAll().size();

        var request = PokemonDto.builder()
                .name(NAME)
                .number(NUMBER)
                .height(HEIGHT)
                .weight(WEIGHT)
                .build();
        ResultActions resultActions = mockMvc.perform(post("/pokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk());

        // read response
        PokemonDto response = deserializeResult(resultActions, PokemonDto.class);

        // assertions
        assertNotNull(response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(NUMBER, response.getNumber());
        assertEquals(HEIGHT, response.getHeight());
        assertEquals(WEIGHT, response.getWeight());

        // database assertions
        int usersAfter = pokemonRepository.findAll().size();
        assertEquals(usersBefore + 1, usersAfter);
        assertEquals(1, usersAfter);
        assertNotNull(pokemonRepository.findById(response.getId()).get());
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = JsonMapper.builder()
                    .addModule(new ParameterNamesModule())
                    .constructorDetector(USE_PROPERTIES_BASED)
                    .build();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T deserializeResult(ResultActions resultActions, Class<T> clazz)
            throws JsonProcessingException, UnsupportedEncodingException {
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        return objectMapper.readValue(contentAsString, clazz);
    }

    @Test
    void getPokemonById_witExistingPokemon_shouldGetPokemon() throws Exception {
        // init test
        PokemonEntity savedPokemon = pokemonRepository.save(PokemonEntity.builder()
                .name(NAME)
                .number(NUMBER)
                .height(HEIGHT)
                .weight(WEIGHT)
                .build());

        ResultActions resultActions = mockMvc.perform(
                get("/pokemon" + "/" +savedPokemon.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // read response
        PokemonDto response = deserializeResult(resultActions, PokemonDto.class);

        // assertions
        assertNotNull(response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(NUMBER, response.getNumber());
        assertEquals(HEIGHT, response.getHeight());
        assertEquals(WEIGHT, response.getWeight());



    }
    @Test
    void createPokemonById_witExistingPokemon_shouldCreatePokemon() throws Exception {
        Mockito.when(pokeApiGateway.getPokemonByName(NAME))
                .thenReturn(PokeApiPokemon.builder()
                        .name(NAME)
                        .number(NUMBER)
                        .height(HEIGHT)
                        .weight(WEIGHT)
                        .build());

        int userBefore = pokemonRepository.findAll().size();

        ResultActions resultActions = mockMvc.perform(post("/pokemon/poke-api").param("name", NAME)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // read response
        PokemonDto response = deserializeResult(resultActions, PokemonDto.class);

        // assertions
        assertNotNull(response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(NUMBER, response.getNumber());
        assertEquals(HEIGHT, response.getHeight());
        assertEquals(WEIGHT, response.getWeight());

        // database assertions
        int usersAfter = pokemonRepository.findAll().size();
        assertEquals(userBefore + 1, usersAfter);
        assertEquals(1, usersAfter);
        assertNotNull(pokemonRepository.findById(response.getId()).get());

    }




}
