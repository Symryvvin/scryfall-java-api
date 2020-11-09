package ru.aizen.mtg.scryfall.api.examples.search;

import ru.aizen.mtg.scryfall.api.client.ScryfallClient;
import ru.aizen.mtg.scryfall.api.client.query.ScryfallQueryException;
import ru.aizen.mtg.scryfall.api.client.query.ScryfallRequestException;
import ru.aizen.mtg.scryfall.api.domain.card.Card;

import java.util.UUID;

public class CardSearch {

	public static void main(String[] args) {
		try {
			ScryfallClient scryfall = ScryfallClient.createDefault();

			Card card = scryfall.card().searchById(UUID.fromString("d4ab8bd7-0405-42d9-ad92-2a7df243ae59"));
			System.out.println(card);
		} catch (ScryfallQueryException | ScryfallRequestException e) {
			e.printStackTrace();
		}
	}

}
