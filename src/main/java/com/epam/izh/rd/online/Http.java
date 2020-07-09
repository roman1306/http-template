package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingService;
import com.epam.izh.rd.online.service.PokemonFightingClubService;
import com.epam.izh.rd.online.service.SimplePokemonFetchingService;
import com.epam.izh.rd.online.service.SimplePokemonFightingClubService;

public class Http {
    public static void main(String[] args) {
        PokemonFetchingService pokemonFetchingService =
                new SimplePokemonFetchingService("https://pokeapi.co/api/v2/");

        PokemonFightingClubService pokemonFightingClubService =
                new SimplePokemonFightingClubService(pokemonFetchingService);

        Pokemon pokemon1 = pokemonFetchingService.fetchByName("pikachu");
        Pokemon pokemon2 = pokemonFetchingService.fetchByName("slowpoke");

        pokemonFightingClubService.doBattle(pokemon1, pokemon2);

    }
}
