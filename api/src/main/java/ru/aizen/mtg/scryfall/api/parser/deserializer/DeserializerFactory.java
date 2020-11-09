package ru.aizen.mtg.scryfall.api.parser.deserializer;

import com.fasterxml.jackson.databind.JsonDeserializer;

import java.lang.reflect.Type;

public class DeserializerFactory {

	public static JsonDeserializer<?> getDeserializer(Type type) {
		DeserializerType deserializerType = DeserializerType.forObjectType(type);
		switch (deserializerType) {
			case BULK_DATA:
				return new BulkDataDeserializer();
			case CARD:
				return new CardDeserializer();
			default:
				throw new UnsupportedOperationException("Invalid value for the \"type\" property.");
		}
	}

}
