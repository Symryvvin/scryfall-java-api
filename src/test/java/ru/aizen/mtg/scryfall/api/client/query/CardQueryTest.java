package ru.aizen.mtg.scryfall.api.client.query;

import org.junit.jupiter.api.Test;
import ru.aizen.mtg.scryfall.api.client.ScryfallClient;
import ru.aizen.mtg.scryfall.api.client.query.card.CardOrder;
import ru.aizen.mtg.scryfall.api.client.query.card.CardOrderDirection;
import ru.aizen.mtg.scryfall.api.domain.card.Card;
import ru.aizen.mtg.scryfall.api.domain.card.CardList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class CardQueryTest {

	@Test
	void byId() throws Exception {
		ScryfallClient scryfall = ScryfallClient.createDefault();

		Card card = scryfall.card().searchById(UUID.fromString("d4ab8bd7-0405-42d9-ad92-2a7df243ae59"));

		System.out.println(card);
	}

	@Test
	void byQuery() throws Exception {
		ScryfallClient scryfall = ScryfallClient.createDefault();

		CardList cardList = scryfall.cardSearch()
		.page(3)
		.sorted(CardOrder.CMC, CardOrderDirection.ASCENDING)
		.searchByQuery("game=paper");
		List<Card> cards = new ArrayList<>(cardList.getData());

		while (cardList.isHasMore()) {
			cardList = scryfall.api( CardList.class).query(cardList.getNextPage());
			cards.addAll(cardList.getData());
			System.out.println(cards.size());
		}



	}

}