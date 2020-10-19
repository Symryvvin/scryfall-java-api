package ru.aizen.mtg.scryfall.api.domain.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.aizen.mtg.scryfall.api.client.ScryfallObject;
import ru.aizen.mtg.scryfall.api.client.ScryfallObjectType;

import java.net.URI;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CardList implements ScryfallObject {

	private final ScryfallObjectType objectType = ScryfallObjectType.LIST;
	private Integer totalCards;
	private boolean hasMore;
	private URI nextPage;
	private List<Card> data;

}
