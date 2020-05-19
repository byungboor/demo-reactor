package com.example.demo.reactor.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class HookMessage {

    @JsonProperty("botName")
    private String header;
    @JsonProperty("text")
    private String body;
    @JsonProperty("botIconImage")
    private String messageImage;

    private HookMessage(String header, String body) {
        this.header = header;
        this.body = body;
        this.messageImage = "https://nhnent.dooray.com/files/2722179512132034767";
    }

    public static HookMessage of(String header, String body) {
        return new HookMessage(header, body);
    }

}
