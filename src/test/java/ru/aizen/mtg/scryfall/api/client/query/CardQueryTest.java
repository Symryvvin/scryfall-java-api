package ru.aizen.mtg.scryfall.api.client.query;

import org.junit.jupiter.api.Test;
import ru.aizen.mtg.scryfall.api.client.ScryfallClient;
import ru.aizen.mtg.scryfall.api.domain.card.Card;

import java.util.UUID;

class CardQueryTest {

	@Test
	void byId() throws Exception {
		ScryfallClient scryfall = ScryfallClient.createDefault();

		Card card = scryfall.card().searchById(UUID.fromString("d4ab8bd7-0405-42d9-ad92-2a7df243ae59"));

		System.out.println(card);
	}

}