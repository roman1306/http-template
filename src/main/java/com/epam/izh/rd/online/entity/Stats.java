package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {

    @JsonProperty("base_stat")
    private short baseStat;

    private Stat stat;

    public short getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(short baseStat) {
        this.baseStat = baseStat;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public static class Stat {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
