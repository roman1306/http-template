package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.factory.SimpleObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class SimplePokemonFetchingService implements PokemonFetchingService {
    private final String url;
    private HttpURLConnection httpURLConnection;

    public SimplePokemonFetchingService(String url) {
        this.url = url;
    }

    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {
        Pokemon pokemon = null;

        try {
            httpURLConnection = (HttpURLConnection) new URL(url + "pokemon/" + name).openConnection();
            httpURLConnection.addRequestProperty("User-agent", "");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
                String json = reader.lines().collect(Collectors.joining());
                ObjectMapper objectMapper = new SimpleObjectMapperFactory().getObjectMapper();
                pokemon = objectMapper.readValue(json, Pokemon.class);
            }

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Pokemon not found");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }

        return pokemon;
    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {
        ByteArrayOutputStream writer = null;
        String imageUrl = fetchByName(name).getImageUrl();

        try {
            httpURLConnection = (HttpURLConnection) new URL(imageUrl).openConnection();
            try (InputStream reader = httpURLConnection.getInputStream()) {
                byte[] buffer = new byte[1];
                writer = new ByteArrayOutputStream();
                while (reader.read(buffer) > 0) {
                    writer.write(buffer);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }

        return writer.toByteArray();
    }
}
