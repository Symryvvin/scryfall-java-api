package ru.aizen.mtg.scryfall.api.examples.bulk;

import ru.aizen.mtg.scryfall.api.client.ScryfallClient;
import ru.aizen.mtg.scryfall.api.client.query.ScryfallQueryException;
import ru.aizen.mtg.scryfall.api.client.query.ScryfallRequestException;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataList;

public class AllBulkData {

	public static void main(String[] args) {
		try {
			ScryfallClient scryfall = ScryfallClient.createDefault();

			BulkDataList bulkDataList = scryfall.bulkData().list();
			System.out.println(bulkDataList);
		} catch (ScryfallQueryException | ScryfallRequestException e) {
			e.printStackTrace();
		}

	}

}
