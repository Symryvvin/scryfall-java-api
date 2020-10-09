package ru.aizen.mtg.scryfall.api.client;

import org.apache.http.impl.client.CloseableHttpClient;
import ru.aizen.mtg.scryfall.api.client.query.BulkDataQuery;

import java.io.Closeable;
import java.io.IOException;

public final class ScryfallClient implements Closeable {

	private final String scheme;
	private final String endpoint;

	private final CloseableHttpClient httpClient;

	public ScryfallClient(CloseableHttpClient httpClient) {
		this.httpClient = httpClient;
		this.scheme = "https";
		this.endpoint = "api.scryfall.com";
	}

	public BulkDataQuery bulkData() {
		return new BulkDataQuery(this);
	}

	public CloseableHttpClient getHttpClient() {
		return httpClient;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public String getScheme() {
		return scheme;
	}

	@Override
	public void close() throws IOException {
		httpClient.close();
	}

}
