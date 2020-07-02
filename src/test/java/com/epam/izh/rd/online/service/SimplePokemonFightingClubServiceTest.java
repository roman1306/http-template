package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimplePokemonFightingClubServiceTest {

    @Test
    void testPokemonFightingService() {
        Pokemon pokemon1 = new Pokemon();
        pokemon1.setHp((short) 50);
        pokemon1.setAttack((short) 20);
        pokemon1.setDefense((short) 5);
        pokemon1.setPokemonId(1);
        pokemon1.setPokemonName("Vasia");
        Pokemon pokemon2 = new Pokemon();
        pokemon2.setHp((short) 100);
        pokemon2.setAttack((short) 40);
        pokemon2.setDefense((short) 10);
        pokemon2.setPokemonId(2);
        pokemon2.setPokemonName("Petya");
        System.out.println("Create pokemons" + "\n" + pokemon1 + "\n" + pokemon2 + "\n");

        new SimplePokemonFightingClubService().doBattle(pokemon1, pokemon2);

    }

}