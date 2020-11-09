package ru.aizen.mtg.scryfall.api.parser.deserializer;

import ru.aizen.mtg.scryfall.api.domain.bulk.BulkData;
import ru.aizen.mtg.scryfall.api.domain.card.Card;

import java.lang.reflect.Type;
import java.util.Arrays;

public enum DeserializerType {

	BULK_DATA(BulkData.class),
	CARD(Card.class),
	SET(null),
	RULINGS(null),
	SYMBOLOGY(null);

	private final Type type;

	DeserializerType(Type type) {
		this.type = type;
	}

	public static <T> DeserializerType forObjectType(Type type) {
		return Arrays.stream(DeserializerType.values())
				.filter(v -> v.type == type)
				.findFirst()
				.orElseThrow(UnsupportedOperationException::new);
	}
}
