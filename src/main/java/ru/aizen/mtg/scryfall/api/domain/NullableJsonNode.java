package ru.aizen.mtg.scryfall.api.domain;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.util.UUID;

public class NullableJsonNode<T> {

	private final Class<T> type;

	public NullableJsonNode(Class<T> type) {
		this.type = type;
	}

	public T parse(JsonNode node, String fieldName) {
		if (node.has(fieldName)) {
			JsonNode field = node.get(fieldName);
			if (type == String.class) {
				return type.cast(field.asText());
			}
			if (type == Integer.class) {
				return type.cast(field.asInt());
			}
			if (type == Boolean.class) {
				return type.cast(field.asBoolean());
			}
		}
		return null;
	}

}
