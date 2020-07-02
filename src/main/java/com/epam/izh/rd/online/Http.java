package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.SimplePokemonFetchingService;
import com.epam.izh.rd.online.service.SimplePokemonFightingClubService;

public class Http {
    public static void main(String[] args) {
        SimplePokemonFetchingService simplePokemonFetchingService = new SimplePokemonFetchingService();
        SimplePokemonFightingClubService simplePokemonFightingClubService = new SimplePokemonFightingClubService();

        Pokemon pokemon1 = simplePokemonFetchingService.fetchByName("charizard");
        Pokemon pokemon2 = simplePokemonFetchingService.fetchByName("charmeleon");

        simplePokemonFightingClubService.doBattle(pokemon1, pokemon2);

    }
}
