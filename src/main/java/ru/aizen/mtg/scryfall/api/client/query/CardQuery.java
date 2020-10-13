package ru.aizen.mtg.scryfall.api.client.query;

import org.apache.http.client.utils.URIBuilder;
import ru.aizen.mtg.scryfall.api.client.ScryfallClient;
import ru.aizen.mtg.scryfall.api.domain.card.Card;

import java.net.URI;
import java.net.URISyntaxException;
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
		ScryfallRequest<Card> request = new ScryfallRequest<>(getClient(), generateMethod("/cards/" + id.toString()), Card.class);
		return request.execute();
	}

	private URI generateMethod(String path) throws ScryfallQueryException {
		try {
			URIBuilder builder = new URIBuilder();
			builder.setScheme(getClient().getScheme());
			builder.setHost(getClient().getEndpoint());
			builder.setPath(path);
			return builder.build();
		} catch (URISyntaxException e) {
			throw new ScryfallQueryException("Query building error", e);
		}
	}

}
