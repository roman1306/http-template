package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SimplePokemonFightingClubService implements PokemonFightingClubService {
    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {
            Pokemon from = p1.getPokemonId() > p2.getPokemonId() ? p1 : p2;
            Pokemon to = p1.getPokemonId() < p2.getPokemonId() ? p1 : p2;;
            Pokemon pokemonBuffer;

            while (from.getHp() > 0) {
                System.out.println("Pokemon " + from.getPokemonName() + " attacking " + to.getPokemonName());
                doDamage(from, to);
                System.out.println("health balance "+ to.getPokemonName() + to.getHp() + "\n");
                pokemonBuffer = from;
                from = to;
                to = pokemonBuffer;
            }

        showWinner(to);
        return to;
    }

    @Override
    public void showWinner(Pokemon winner) {
        SimplePokemonFetchingService pokemonFetchingService = new SimplePokemonFetchingService();
        byte[] pokemonImage = pokemonFetchingService.getPokemonImage(winner.getPokemonName());
        String path = "src/winner.png";

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path))) {
            out.write(pokemonImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        short damageTaken = (short) (from.getAttack() - (from.getAttack() * to.getDefense() / 100));
        to.setHp((short) (to.getHp() - damageTaken));
    }
}
