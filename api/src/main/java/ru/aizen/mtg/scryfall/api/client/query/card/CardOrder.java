package ru.aizen.mtg.scryfall.api.client.query.card;

public enum CardOrder {
	NAME,
	SET,
	RELEASED,
	RARITY,
	COLOR,
	USD,
	TIX,
	EUR,
	CMC,
	POWER,
	TOUGHNESS,
	EDHREC,
	ARTIST;

	String value() {
		return this.name().toLowerCase();
	}
}

