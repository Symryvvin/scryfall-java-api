package ru.aizen.mtg.scryfall.api.domain.bulk;

public enum BulkDataType {
	ORACLE_CARDS("oracle-cards"),
	UNIQUE_ARTWORK("unique-artwork"),
	DEFAULT_CARDS("default-cards"),
	ALL_CARDS("all-cards"),
	RULINGS("rulings");

	private final String name;

	BulkDataType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
