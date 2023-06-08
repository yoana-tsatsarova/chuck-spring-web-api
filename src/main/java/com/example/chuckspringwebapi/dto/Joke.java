package com.example.chuckspringwebapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record Joke(@JsonProperty("text") @NotNull String text, String responseAt, UUID id) {
}