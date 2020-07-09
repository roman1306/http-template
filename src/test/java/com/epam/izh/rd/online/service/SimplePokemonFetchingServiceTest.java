package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.*;

class SimplePokemonFetchingServiceTest {
    private static PokemonFetchingService pokemonFetchingService;
    private static WireMockServer wireMockServer;
    private Pokemon pokemon;

    @BeforeAll

    static void beforeAll() {
        pokemonFetchingService = new SimplePokemonFetchingService("http://localhost:8080/api/v2/");

        wireMockServer = new WireMockServer(options());
        wireMockServer.start();

        stubFor(get(urlEqualTo("/api/v2/pokemon/pikachu"))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("pikachu.json")
                )
        );

        stubFor(get(urlEqualTo("/sprites/pokemon/25.png"))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "image/png")
                                .withBodyFile("pikachu.png")

                )
        );
    }

    @AfterAll
    static void tearDownAll() {
        wireMockServer.stop();
    }

    @BeforeEach
    void setup() {
        pokemon = new Pokemon();
        pokemon.setPokemonId(25);
        pokemon.setPokemonName("pikachu");
        pokemon.setHp((short) 35);
        pokemon.setAttack((short) 55);
        pokemon.setDefense((short) 40);
        pokemon.setImageUrl("http://localhost:8080/sprites/pokemon/25.png");
    }

    @Test
    void testFetchByName() {
        assertEquals(pokemon, pokemonFetchingService.fetchByName("pikachu"));
    }

    @Test
    void testExceptionFetchByName() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> pokemonFetchingService.fetchByName("Vasya"));
        assertEquals("Pokemon not found", exception.getMessage());
    }

    @Test
    void getPokemonImage() throws NoSuchAlgorithmException {
        byte[] pokemonImage = pokemonFetchingService.getPokemonImage("pikachu");
        byte[] hash = MessageDigest.getInstance("md5").digest(pokemonImage);
        String hashHex = Hex.encodeHexString(hash).toUpperCase();

        assertEquals("755DE1A551FB9C42E7B3E8A665DD7FE0", hashHex);
    }
}