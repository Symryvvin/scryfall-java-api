package ru.aizen.mtg.scryfall.api.examples.search;

import ru.aizen.mtg.scryfall.api.client.ScryfallClient;
import ru.aizen.mtg.scryfall.api.client.query.DirectApiQuery;
import ru.aizen.mtg.scryfall.api.client.query.ScryfallQueryException;
import ru.aizen.mtg.scryfall.api.client.query.ScryfallRequestException;
import ru.aizen.mtg.scryfall.api.client.query.card.CardOrder;
import ru.aizen.mtg.scryfall.api.client.query.card.CardOrderDirection;
import ru.aizen.mtg.scryfall.api.domain.card.Card;
import ru.aizen.mtg.scryfall.api.domain.card.CardList;

import java.net.URI;
import java.util.List;

public class CardTextMultiPageSearch {

	public static void main(String[] args) {
		try {
			ScryfallClient scryfall = ScryfallClient.createDefault();

			CardList cardList = scryfall.cardSearch()
					.query("game=paper")
					.page(3)
					.sorted(CardOrder.CMC, CardOrderDirection.ASCENDING)
					.execute();

			List<Card> cards = cardList.getData();
			System.out.println(cards.size());

			if (cardList.isHasMore()) {

				URI nextPage = cardList.getNextPage();
				DirectApiQuery<CardList> directApiQuery = scryfall.query(nextPage, CardList.class);
				CardList nextPageList = directApiQuery.execute();

				cards.addAll(nextPageList.getData());

				System.out.println(cards.size());
				System.out.println("has more pages");
			}
		} catch (ScryfallQueryException | ScryfallRequestException e) {
			e.printStackTrace();
		}
	}

}
