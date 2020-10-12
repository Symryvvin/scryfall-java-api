package ru.aizen.mtg.scryfall.api.domain;

import com.fasterxml.jackson.databind.JsonNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayJsonNode<T> {

	private final Class<T> type;

	public ArrayJsonNode(Class<T> type) {
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public T[] parse(JsonNode node, String fieldName, ValueConverter<T> converter) {
		List<T> list = new ArrayList<>();
		if (node.has(fieldName)) {
			node.get(fieldName).forEach(n -> list.add(converter.convert(n)));
		}
		return list.toArray((T[]) Array.newInstance(type, list.size()));
	}

	public interface ValueConverter<T> {

		T convert(JsonNode node);

	}

}
