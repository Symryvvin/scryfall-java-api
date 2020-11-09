package ru.aizen.mtg.scryfall.api.domain.list;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.aizen.mtg.scryfall.api.client.ScryfallObject;
import ru.aizen.mtg.scryfall.api.client.ScryfallObjectType;

import java.net.URI;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ScryfallList<T> implements ScryfallObject {

	private final ScryfallObjectType objectType = ScryfallObjectType.LIST;
	private Integer totalCards;
	private boolean hasMore;
	private URI nextPage;
	private List<T> data;

}
