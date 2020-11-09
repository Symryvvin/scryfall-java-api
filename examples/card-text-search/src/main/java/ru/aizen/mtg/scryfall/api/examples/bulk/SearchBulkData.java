package ru.aizen.mtg.scryfall.api.examples.bulk;

import ru.aizen.mtg.scryfall.api.client.ScryfallClient;
import ru.aizen.mtg.scryfall.api.client.query.ScryfallQueryException;
import ru.aizen.mtg.scryfall.api.client.query.ScryfallRequestException;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkData;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataType;

import java.util.UUID;

public class SearchBulkData {

	public static void main(String[] args) {
		try {
			ScryfallClient scryfall = ScryfallClient.createDefault();

			BulkData byType = scryfall.bulkData().byType(BulkDataType.ALL_CARDS);
			System.out.println(byType);

			BulkData byId = scryfall.bulkData().byId(UUID.fromString("27bf3214-1271-490b-bdfe-c0be6c23d02e"));
			System.out.println(byId);

		} catch (ScryfallQueryException | ScryfallRequestException e) {
			e.printStackTrace();
		}
	}

}
