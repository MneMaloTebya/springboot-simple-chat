package com.github.mnemalotebya.spingbootsimplechat.dto;

import lombok.Data;

@Data
public class DtoMessage {
    private String text;
    private String datetime;
    private String username;

    public DtoMessage(String text, String datetime, String username) {
        this.text = text;
        this.datetime = datetime;
        this.username = username;
    }

    public DtoMessage() {
    }
}
