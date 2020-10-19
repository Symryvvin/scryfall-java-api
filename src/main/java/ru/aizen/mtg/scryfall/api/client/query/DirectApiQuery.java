package ru.aizen.mtg.scryfall.api.client.query;

import ru.aizen.mtg.scryfall.api.client.ScryfallClient;

import java.net.URI;

public class DirectApiQuery<T> {

	private final ScryfallClient client;
	private final Class<T> type;

	public DirectApiQuery(ScryfallClient client, Class<T> type) {
		this.client = client;
		this.type = type;
	}

	public T query(URI uri) throws ScryfallRequestException {
		return new ScryfallRequest<T>(client, uri, type).execute();
	}

}
