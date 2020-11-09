package ru.aizen.mtg.scryfall.api.client.query.card;

public enum CardUnique {
	CARDS,
	ART,
	PRINTS;

	String value() {
		return this.name().toLowerCase();
	}
}