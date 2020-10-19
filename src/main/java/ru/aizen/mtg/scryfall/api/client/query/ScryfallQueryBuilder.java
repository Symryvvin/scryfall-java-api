package ru.aizen.mtg.scryfall.api.client.query;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import ru.aizen.mtg.scryfall.api.client.ScryfallClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public abstract class ScryfallQueryBuilder {

	private final ScryfallClient client;

	public ScryfallQueryBuilder(ScryfallClient client) {
		this.client = client;
	}

	protected ScryfallClient getClient() {
		return client;
	}

	protected URI method(String path, NameValuePair... params) throws ScryfallQueryException {
		try {
			URIBuilder builder = new URIBuilder();
			builder.setScheme(getClient().getScheme());
			builder.setHost(getClient().getEndpoint());
			builder.setPath(path);
			builder.addParameters(Arrays.asList(params));
			return builder.build();
		} catch (URISyntaxException e) {
			throw new ScryfallQueryException("Query building error", e);
		}
	}

}
