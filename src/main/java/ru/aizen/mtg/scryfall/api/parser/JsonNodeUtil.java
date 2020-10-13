package ru.aizen.mtg.scryfall.api.parser;

import com.fasterxml.jackson.databind.JsonNode;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JsonNodeUtil {

	public static <T> T parseSimpleNode(JsonNode node, String fieldName, Class<T> type) {
		if (node.has(fieldName)) {
			JsonNode field = node.get(fieldName);
			if (type == String.class) {
				return type.cast(field.asText());
			}
			if (type == Integer.class) {
				return type.cast(field.asInt());
			}
			if (type == Double.class) {
				return type.cast(field.asDouble());
			}
			if (type == Boolean.class) {
				return type.cast(field.asBoolean());
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] parseArrayNode(JsonNode node, String fieldName, Class<T> type, ValueConverter<T> converter) {
		List<T> list = new ArrayList<>();
		if (node.has(fieldName)) {
			node.get(fieldName).forEach(n -> list.add(converter.convert(n)));
		}
		return list.toArray((T[]) Array.newInstance(type, list.size()));
	}

	public static UUID parseUUID(JsonNode node, String fieldName) {
		if (node.has(fieldName)) {
			return UUID.fromString(node.get(fieldName).asText());
		}
		return null;
	}

	public static LocalDate parseLocalDate(JsonNode node, String fieldName, DateTimeFormatter formatter) {
		if (node.has(fieldName)) {
			return LocalDate.parse(node.get(fieldName).asText(), formatter);
		}
		return null;
	}

	public static LocalDateTime parseLocalDateTime(JsonNode node, String fieldName, DateTimeFormatter formatter) {
		if (node.has(fieldName)) {
			return LocalDateTime.parse(node.get(fieldName).asText(), formatter);
		}
		return null;
	}



}
