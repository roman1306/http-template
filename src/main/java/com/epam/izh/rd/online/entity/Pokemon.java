package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Покемон. Поля должны заполняться из JSON, который возвратит внешний REST-service
 * Для маппинка значений из массива stats рекомендуется использовать отдельный класс Stat и аннотацию @JsonCreator
 */

@Data
@NoArgsConstructor
public class Pokemon {

    /**
     * Уникальный идентификатор, маппится из поля pokemonId
     */

    private long pokemonId;

    /**
     * Имя покемона, маппится из поля pokemonName
     */

    private String pokemonName;

    /**
     * Здоровье покемона, маппится из массива объектов stats со значением name: "hp"
     */
    private short hp;

    /**
     * Атака покемона, маппится из массива объектов stats со значением name: "attack"
     */
    private short attack;

    /**
     * Защита покемона, маппится из массива объектов stats со значением name: "defense"
     */
    private short defense;

    private String imageUrl;

    @JsonCreator
    public Pokemon(@JsonProperty("id") long pokemonId, @JsonProperty("name") String pokemonName,
                   @JsonProperty("stats") List<Stats> stats, @JsonProperty("sprites") Sprites sprites) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        Map<String, Short> statistics = stats.stream().collect(toMap
                (stat -> stat.getStat().getName(), Stats::getBaseStat));
        this.hp = statistics.get("hp");
        this.attack = statistics.get("attack");
        this.defense = statistics.get("defense");
        this.imageUrl = sprites.getImageUrl();
    }
}
