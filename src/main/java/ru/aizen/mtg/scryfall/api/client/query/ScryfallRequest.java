package ru.aizen.mtg.scryfall.api.client.query;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import ru.aizen.mtg.scryfall.api.client.ScryfallClient;

import java.io.IOException;
import java.net.URI;

public class ScryfallRequest<T> {

	private final ScryfallClient client;
	private final URI uri;
	private final Class<T> type;

	public ScryfallRequest(ScryfallClient client, URI uri, Class<T> type) {
		this.client = client;
		this.uri = uri;
		this.type = type;
	}

	public T execute() throws ScryfallRequestException {
		try {
			return client.getObjectMapper().readValue(responseEntityAsString(), type);
		} catch (JsonProcessingException e) {
			throw new ScryfallRequestException(e);
		}
	}

	public String responseEntityAsString() throws ScryfallRequestException {
		try {
			HttpGet request = new HttpGet(uri);
			HttpResponse response = client.getTransportClient().execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				throw new ScryfallRequestException("Internal API server error. Wrong status code: " + statusCode + ".");
			}

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity);
			} else {
				throw new ScryfallRequestException("Response content is empty");
			}

		} catch (IOException e) {
			throw new ScryfallRequestException(e);
		}
	}

}
