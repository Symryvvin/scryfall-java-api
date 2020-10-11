package ru.aizen.mtg.scryfall.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import ru.aizen.mtg.scryfall.api.client.query.BulkDataQuery;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkData;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataDeserializer;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataList;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataListDeserializer;

public final class ScryfallClient {

	private final String scheme;
	private final String endpoint;

	private final HttpClient transportClient;
	private final ObjectMapper objectMapper;

	private ScryfallClient(Builder builder) {
		this.transportClient = builder.transportClient;
		this.objectMapper = builder.objectMapper;
		this.scheme = "https";
		this.endpoint = "api.scryfall.com";
	}

	public static ScryfallClient createDefault() {
		ObjectMapper mapper = new ObjectMapper();

		SimpleModule module = new SimpleModule();
		module.addDeserializer(BulkDataList.class, new BulkDataListDeserializer());
		module.addDeserializer(BulkData.class, new BulkDataDeserializer());

		mapper.registerModule(module);

		return new Builder().transportClient(HttpClients.createDefault())
				.objectMapper(mapper)
				.build();
	}

	public static Builder custom() {
		return new Builder();
	}

	public BulkDataQuery bulkData() {
		return new BulkDataQuery(this);
	}

	public HttpClient getTransportClient() {
		return transportClient;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public String getScheme() {
		return scheme;
	}

	public static class Builder {

		private HttpClient transportClient;
		private ObjectMapper objectMapper;

		public Builder transportClient(HttpClient transportClient) {
			this.transportClient = transportClient;
			return this;
		}

		public Builder objectMapper(ObjectMapper objectMapper) {
			this.objectMapper = objectMapper;
			return this;
		}

		public ScryfallClient build() {
			return new ScryfallClient(this);
		}

	}

}
