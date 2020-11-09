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
 * Card Face Objects
 * Multiface cards have a card_faces property containing at least two Card Face objects.
 * Those objects have the following properties:
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CardFace implements ScryfallObject {

	private final ScryfallObjectType objectType = ScryfallObjectType.CARD_FACE;
	/**
	 * Nullable
	 * The name of the illustrator of this card face. Newly spoiled cards may not have this field yet.
	 */
	private String artist;
	/**
	 * Nullable
	 * The colors in this face’s color indicator, if any.
	 */
	private Color[] color_indicator;
	/**
	 * Nullable
	 * This face’s colors, if the game defines colors for the individual face of this card.
	 */
	private Color[] colors;
	/**
	 * Nullable
	 * The flavor text printed on this face, if any.
	 */
	private String flavor_text;
	/**
	 * Nullable
	 * A unique identifier for the card face artwork that remains consistent across reprints.
	 * Newly spoiled cards may not have this field yet.
	 */
	private UUID illustration_id;
	/**
	 * Nullable
	 * An object providing URIs to imagery for this face, if this is a double-sided card.
	 * If this card is not double-sided, then the image_uris property will be part of the parent object instead.
	 */
	private URI[] image_uris;
	/**
	 * Nullable
	 * This face’s loyalty, if any.
	 */
	private String loyalty;
	/**
	 * The mana cost for this face. This value will be any empty string "" if the cost is absent.
	 * Remember that per the game rules, a missing mana cost and a mana cost of {0} are different values.
	 */
	private String mana_cost;
	/**
	 * The name of this particular face.
	 */
	private String name;
	/**
	 * Nullable
	 * The Oracle text for this face, if any.
	 */
	private String oracle_text;
	/**
	 * Nullable
	 * This face’s power, if any. Note that some cards have powers that are not numeric, such as *.
	 */
	private String power;
	/**
	 * Nullable
	 * The localized name printed on this face, if any.
	 */
	private String printed_name;
	/**
	 * Nullable
	 * The localized text printed on this face, if any.
	 */
	private String printed_text;
	/**
	 * Nullable
	 * The localized type line printed on this face, if any.
	 */
	private String printed_type_line;
	/**
	 * Nullable
	 * This face’s toughness, if any.
	 */
	private String toughness;
	/**
	 * The type line of this particular face.
	 */
	private String type_line;
	/**
	 * Nullable
	 * The watermark on this particulary card face, if any
	 */
	private String watermark;

}
