package ru.aizen.mtg.scryfall.api.client.query;

import ru.aizen.mtg.scryfall.api.client.ScryfallClient;

public abstract class ScryfallQueryBuilder {

	private final ScryfallClient client;

	public ScryfallQueryBuilder(ScryfallClient client) {
		this.client = client;
	}

	protected ScryfallClient getClient() {
		return client;
	}

}
