package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sprites {

    @JsonProperty("front_default")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
