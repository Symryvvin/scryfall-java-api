package ru.aizen.mtg.scryfall.api.client.query.card;

import org.apache.http.message.BasicNameValuePair;
import ru.aizen.mtg.scryfall.api.client.ScryfallClient;
import ru.aizen.mtg.scryfall.api.client.query.ScryfallQueryBuilder;
import ru.aizen.mtg.scryfall.api.client.query.ScryfallQueryException;
import ru.aizen.mtg.scryfall.api.client.query.ScryfallRequest;
import ru.aizen.mtg.scryfall.api.client.query.ScryfallRequestException;
import ru.aizen.mtg.scryfall.api.domain.card.CardList;

public class CardSearchQuery extends ScryfallQueryBuilder {

	private CardUnique unique = CardUnique.CARDS;
	private CardOrder order = CardOrder.NAME;
	private CardOrderDirection direction = CardOrderDirection.AUTO;
	private int page = 1;
	public boolean includeExtras = false;
	public boolean includeMultilingual = false;
	public boolean includeVariations = false;

	public CardSearchQuery(ScryfallClient client) {
		super(client);
	}

	/**
	 * GET /cards/search
	 *
	 * @param query A fulltext search query. Make sure that your parameter is properly encoded.
	 * @return Returns a List object containing Cards found using a fulltext search string.
	 * This string supports @see <a href="https://scryfall.com/docs/syntax">fulltext search system</a> that the main site uses.
	 *
	 * This method is paginated, returning 175 cards at a time.
	 */
	public CardList searchByQuery(String query) throws ScryfallQueryException, ScryfallRequestException {

		ScryfallRequest<CardList> request = new ScryfallRequest<>(
				getClient(),
				method("/cards/search",
						new BasicNameValuePair("q", query),
						new BasicNameValuePair("unique", unique.value()),
						new BasicNameValuePair("order", order.value()),
						new BasicNameValuePair("direction", direction.value()),
						new BasicNameValuePair("page", String.valueOf(page)),
						new BasicNameValuePair("include_extras", String.valueOf(includeExtras)),
						new BasicNameValuePair("include_multilingual", String.valueOf(includeMultilingual)),
						new BasicNameValuePair("include_variations", String.valueOf(includeVariations))
				),
				CardList.class);
		return request.execute();
	}

	public CardSearchQuery sorted(CardOrder order) {
		return sorted(order, CardOrderDirection.AUTO);
	}

	/**
	 * @param order parameter determines how Scryfall should sort the returned cards.
	 * @param direction parameter to choose which direction the sorting should occur.
	 */
	public CardSearchQuery sorted(CardOrder order, CardOrderDirection direction) {
		this.order = order;
		this.direction = direction;
		return this;
	}

	/**
	 *
	 * @param page the page number to return, default 1.
	 */
	public CardSearchQuery page(int page) {
		this.page = page;
		return this;
	}

	/**
	 * @param unique parameter specifies if Scryfall should remove “duplicate” results in your query.
	 */
	public CardSearchQuery unique(CardUnique unique) {
		this.unique = unique;
		return this;
	}

	public CardSearchQuery includeExtras() {
		this.includeExtras = true;
		return this;
	}

	public CardSearchQuery includeMultilingual() {
		this.includeMultilingual = true;
		return this;
	}

	public CardSearchQuery includeVariations() {
		this.includeVariations = true;
		return this;
	}

}
