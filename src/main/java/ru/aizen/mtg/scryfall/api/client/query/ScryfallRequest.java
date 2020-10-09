package ru.aizen.mtg.scryfall.api.client.query;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
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
			Gson gson = new Gson();
			return gson.fromJson(responseEntityAsString(), type);
		} catch (JsonSyntaxException e) {
			throw new ScryfallRequestException(e);
		}
	}

	public String responseEntityAsString() throws ScryfallRequestException {
		HttpGet request = new HttpGet(uri);

		try (CloseableHttpResponse response = client.getHttpClient().execute(request)) {

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
