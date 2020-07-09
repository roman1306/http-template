package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stats {

    @JsonProperty("base_stat")
    private short baseStat;

    private Stat stat;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Stat {

        private String name;

    }
}
