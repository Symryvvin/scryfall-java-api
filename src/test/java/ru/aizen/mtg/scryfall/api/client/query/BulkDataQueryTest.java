package ru.aizen.mtg.scryfall.api.client.query;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import ru.aizen.mtg.scryfall.api.client.ScryfallClient;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkData;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataList;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataType;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BulkDataQueryTest {

	@Test
	void list() throws Exception {
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		ScryfallClient scryfall = new ScryfallClient(closeableHttpClient);

		BulkDataList bulkDataList = scryfall.bulkData().list();

		assertNotNull(bulkDataList);
		assertFalse(bulkDataList.getData().isEmpty());
	}

	@Test
	void byType() throws Exception {
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		ScryfallClient scryfall = new ScryfallClient(closeableHttpClient);

		BulkData bulkData = scryfall.bulkData().byType(BulkDataType.ALL_CARDS);

		assertNotNull(bulkData);
	}

	@Test
	void byTypeAsFile() {
	}

	@Test
	void byId() throws Exception {
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		ScryfallClient scryfall = new ScryfallClient(closeableHttpClient);

		BulkData bulkData = scryfall.bulkData().byId(UUID.fromString("27bf3214-1271-490b-bdfe-c0be6c23d02e"));
		System.out.println(scryfall.bulkData().byId(UUID.fromString("27bf3214-1271-490b-bdfe-c0be6c23d02e")));

		assertNotNull(bulkData);
	}

	@Test
	void byIdAsFile() {
	}

}