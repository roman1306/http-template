package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SimplePokemonFetchingServiceTest {

    @Test
    void testCreateJSON () {
        SimplePokemonFetchingService fetchingService = new SimplePokemonFetchingService();
        Pokemon pokemon = fetchingService.fetchByName("slowpoke");
        System.out.println(pokemon);

    }

}