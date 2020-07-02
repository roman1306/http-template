package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.SimpleObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class SimplePokemonFetchingService implements PokemonFetchingService {
    private String url = "https://pokeapi.co/api/v2/";
    private HttpURLConnection httpURLConnection;
    private BufferedReader reader;

    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {
        Pokemon pokemon = null;

        try {
            httpURLConnection = (HttpURLConnection) new URL(url + "pokemon/" + name).openConnection();
            httpURLConnection.addRequestProperty("User-agent", "");
            reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String json = reader.lines().collect(Collectors.joining());
            ObjectMapper objectMapper = new SimpleObjectMapperFactory().getObjectMapper();
            pokemon = objectMapper.readValue(json, Pokemon.class);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpURLConnection.disconnect();
        }

        return pokemon;
    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {
        byte[] bytesImage = null;
        String imageUrl = fetchByName(name).getImageUrl();
        try {
            httpURLConnection = (HttpURLConnection) new URL(imageUrl).openConnection();
            bytesImage = new byte[httpURLConnection.getContentLength()];
            httpURLConnection.getInputStream().read(bytesImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytesImage;
    }
}
