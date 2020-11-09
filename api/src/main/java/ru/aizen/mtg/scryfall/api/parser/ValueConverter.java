package ru.aizen.mtg.scryfall.api.parser;

import com.fasterxml.jackson.databind.JsonNode;

public interface ValueConverter<T> {

	T convert(JsonNode node);

}