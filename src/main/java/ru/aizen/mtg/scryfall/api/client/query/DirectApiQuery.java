package ru.aizen.mtg.scryfall.api.client.query;

import ru.aizen.mtg.scryfall.api.client.ScryfallClient;

import java.net.URI;

public class DirectApiQuery<T> {

	private final ScryfallClient client;
	private final Class<T> type;
	private final URI uri;

	public DirectApiQuery(ScryfallClient client, URI uri, Class<T> type) {
		this.client = client;
		this.uri = uri;
		this.type = type;
	}

	public T execute() throws ScryfallRequestException {
		return new ScryfallRequest<T>(client, uri, type).execute();
	}

}
