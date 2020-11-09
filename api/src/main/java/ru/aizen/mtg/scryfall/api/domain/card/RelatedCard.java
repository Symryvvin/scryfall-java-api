package ru.aizen.mtg.scryfall.api.domain.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.aizen.mtg.scryfall.api.client.ScryfallObject;
import ru.aizen.mtg.scryfall.api.client.ScryfallObjectType;

import java.net.URI;
import java.util.UUID;

/**
 * Related Card Objects
 * Cards that are closely related to other cards (because they call them by name, or generate a token, or meld, etc)
 * have a all_parts property that contains Related Card objects. Those objects have the following properties:
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RelatedCard implements ScryfallObject {

	private final ScryfallObjectType objectType = ScryfallObjectType.RELATED_CARD;

	/**
	 * An unique ID for this card in Scryfall’s database.
	 */
	private UUID id;
	/**
	 * A field explaining what role this card plays in this relationship, one of token, meld_part, meld_result, or combo_piece.
	 */
	private String component;
	/**
	 * The name of this particular related card.
	 */
	private String name;
	/**
	 * The type line of this card.
	 */
	private String typeLine;
	/**
	 * A URI where you can retrieve a full object describing this card on Scryfall’s API.
	 */
	private URI uri;

}
