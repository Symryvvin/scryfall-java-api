package ru.aizen.mtg.scryfall.api.client.query.card;

public enum CardOrderDirection {
	AUTO,
	ASCENDING,
	DESCENDING;

	String value() {
		return this.name().toLowerCase();
	}
}