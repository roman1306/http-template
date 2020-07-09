package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Sprites {

    @JsonProperty("front_default")
    private String imageUrl;
}
