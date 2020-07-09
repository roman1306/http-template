package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.entity.Sprites;
import com.epam.izh.rd.online.entity.Stats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class SimplePokemonFightingClubServiceTest {
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private PokemonFightingClubService pokemonFightingClubService;
    private PokemonFetchingService pokemonFetchingService;

    @BeforeEach
    void setup() {
        pokemon1 = new Pokemon(1, "pikachu",
                Arrays.asList(
                        new Stats((short) 50, new Stats.Stat("hp")),
                        new Stats((short) 25, new Stats.Stat("attack")),
                        new Stats((short) 5, new Stats.Stat("defense"))),
                new Sprites());

        pokemon2 = new Pokemon(2, "diglett",
                Arrays.asList(
                        new Stats((short) 40, new Stats.Stat("hp")),
                        new Stats((short) 15, new Stats.Stat("attack")),
                        new Stats((short) 20, new Stats.Stat("defense"))),
                new Sprites());

        pokemonFetchingService = new SimplePokemonFetchingService("https://pokeapi.co/api/v2/");
        pokemonFightingClubService = new SimplePokemonFightingClubService(pokemonFetchingService);
    }

    @Test
    void testDoBattle() {
        assertEquals(pokemon1.getPokemonName(),
                pokemonFightingClubService.doBattle(pokemon1, pokemon2).getPokemonName());
    }

    @Test
    void testShowWinner() {
        Path pathForImage = Paths.get("src/winners/" + pokemon1.getPokemonName() + ".png");
        byte[] bytesImage = new byte[0];

        try {
            if (Files.deleteIfExists(pathForImage)) {
                pokemonFightingClubService.showWinner(pokemon1);
                bytesImage = Files.readAllBytes(pathForImage);
            } else {
                pokemonFightingClubService.showWinner(pokemon1);
                bytesImage = Files.readAllBytes(pathForImage);
                assertTrue(Files.deleteIfExists(pathForImage));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(468062979, Arrays.hashCode(bytesImage));
    }

    @Test
    void doDamage() {
        pokemonFightingClubService.doDamage(pokemon1, pokemon2);
        assertEquals(20, pokemon2.getHp());
    }
}