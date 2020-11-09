package ru.aizen.mtg.scryfall.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import ru.aizen.mtg.scryfall.api.client.query.BulkDataQuery;
import ru.aizen.mtg.scryfall.api.client.query.DirectApiQuery;
import ru.aizen.mtg.scryfall.api.client.query.card.CardQuery;
import ru.aizen.mtg.scryfall.api.client.query.card.CardSearchQuery;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkData;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataList;
import ru.aizen.mtg.scryfall.api.domain.card.Card;
import ru.aizen.mtg.scryfall.api.domain.card.CardList;
import ru.aizen.mtg.scryfall.api.parser.deserializer.BulkDataDeserializer;
import ru.aizen.mtg.scryfall.api.parser.deserializer.BulkDataListDeserializer;
import ru.aizen.mtg.scryfall.api.parser.deserializer.CardDeserializer;
import ru.aizen.mtg.scryfall.api.parser.deserializer.CardListDeserializer;

import java.net.URI;

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
		module.addDeserializer(CardList.class, new CardListDeserializer());
		module.addDeserializer(Card.class, new CardDeserializer());

		mapper.registerModule(module);

		return new Builder().transportClient(HttpClients.createDefault())
				.objectMapper(mapper)
				.build();
	}

	public static Builder custom() {
		return new Builder();
	}

	public <T> DirectApiQuery<T> query(URI uri, Class<T> type) {
		return new DirectApiQuery<>(this, uri, type);
	}

	public BulkDataQuery bulkData() {
		return new BulkDataQuery(this);
	}

	public CardQuery card() {
		return new CardQuery(this);
	}

	public CardSearchQuery cardSearch() {
		return new CardSearchQuery(this);
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
