package ru.aizen.mtg.scryfall.api.client.query;

import org.junit.jupiter.api.Test;
import ru.aizen.mtg.scryfall.api.client.ScryfallClient;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkData;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataList;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataType;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BulkDataQueryTest {

	@Test
	void list() throws Exception {
		ScryfallClient scryfall = ScryfallClient.createDefault();

		BulkDataList bulkDataList = scryfall.bulkData().list();

		assertNotNull(bulkDataList);
		assertFalse(bulkDataList.getData().isEmpty());
	}

	@Test
	void byType() throws Exception {
		ScryfallClient scryfall = ScryfallClient.createDefault();

		BulkData bulkData = scryfall.bulkData().byType(BulkDataType.ALL_CARDS);

		assertNotNull(bulkData);
	}

	@Test
	void byTypeAsFile() {
	}

	@Test
	void byId() throws Exception {
		ScryfallClient scryfall = ScryfallClient.createDefault();

		BulkData bulkData = scryfall.bulkData().byId(UUID.fromString("27bf3214-1271-490b-bdfe-c0be6c23d02e"));

		assertNotNull(bulkData);
	}

	@Test
	void byIdAsFile() {
	}

}