package ru.aizen.mtg.scryfall.api.client.query;

import ru.aizen.mtg.scryfall.api.client.ScryfallClient;
import ru.aizen.mtg.scryfall.api.domain.card.Card;

import java.util.UUID;

public class CardQuery extends ScryfallQueryBuilder {

	public CardQuery(ScryfallClient client) {
		super(client);
	}

	/**
	 * GET /cards/:id
	 *
	 * @param id UUID		The Scryfall ID.
	 * @return a single card with the given Scryfall ID.
	 */
	public Card searchById(UUID id) throws ScryfallQueryException, ScryfallRequestException {
		ScryfallRequest<Card> request = new ScryfallRequest<>(getClient(), method("/cards/" + id.toString()), Card.class);
		return request.execute();
	}

}
