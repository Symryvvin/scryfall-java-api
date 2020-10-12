package ru.aizen.mtg.scryfall.api.domain.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.aizen.mtg.scryfall.api.client.ScryfallObject;
import ru.aizen.mtg.scryfall.api.client.ScryfallObjectType;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Card objects represent individual Magic: The Gathering cards that players could obtain and add to their collection.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Card implements ScryfallObject {

	private final ScryfallObjectType objectType = ScryfallObjectType.CARD;
	// Core Fields. Cards have the following core properties
	/**
	 * Nullable
	 * This card’s Arena ID, if any. A large percentage of cards are not available on Arena and do not have this ID.
	 */
	private Integer arenaId;
	/**
	 * A unique ID for this card in Scryfall’s database
	 */
	private UUID id;
	/**
	 * A language code for this printing.
	 */
	private String lang;
	/**
	 * Nullable
	 * This card’s Magic Online ID (also known as the Catalog ID), if any.
	 * A large percentage of cards are not available on Magic Online and do not have this ID.
	 */
	private Integer mtgoId;
	/**
	 * Nullable
	 * This card’s foil Magic Online ID (also known as the Catalog ID), if any.
	 * A large percentage of cards are not available on Magic Online and do not have this ID.
	 */
	private Integer mtgoFoilId;
	/**
	 * Nullable
	 * This card’s multiverse IDs on Gatherer, if any, as an array of integers.
	 * Note that Scryfall includes many promo cards, tokens, and other esoteric objects that do not have these identifiers.
	 */
	private Integer[] multiverseIds;
	/**
	 * Nullable
	 * This card’s ID on TCGplayer’s API, also known as the productId.
	 */
	private Integer tcgplayerId;
	/**
	 * Nullable
	 * This card’s ID on Cardmarket’s API, also known as the idProduct.
	 */
	private Integer cardmarketId;
	/**
	 * A unique ID for this card’s oracle identity.
	 * This value is consistent across reprinted card editions, and
	 * unique among different cards with the same name (tokens, Unstable variants, etc).
	 */
	private UUID oracleId;
	/**
	 * A link to where you can begin paginating all re/prints for this card on Scryfall’s API.
	 */
	private URI printsSearchUri;
	/**
	 * A link to this card’s rulings list on Scryfall’s API.
	 */
	private URI rulingsUri;
	/**
	 * A link to this card’s permapage on Scryfall’s website.
	 */
	private URI scryfallUri;
	/**
	 * A link to this card object on Scryfall’s API.
	 */
	private URI uri;

	// Gameplay Fields. Cards have the following properties relevant to the game rules:
	/**
	 * Nullable
	 * If this card is closely related to other cards, this property will be an array with Related Card Objects.
	 */
	private RelatedCard[] allParts;
	/**
	 * Nullable
	 * An array of Card Face objects, if this card is multifaced.
	 */
	private CardFace[] cardFaces;
	/**
	 * The card’s converted mana cost. Note that some funny cards have fractional mana costs.
	 */
	private Integer cmc;
	/**
	 * This card’s color identity.
	 */
	private Color[] colorIdentity;
	/**
	 * Nullable
	 * The colors in this card’s color indicator, if any.
	 * A null value for this field indicates the card does not have one.
	 */
	private Color[] colorIndicator;
	/**
	 * Nullable
	 * This card’s colors, if the overall card has colors defined by the rules.
	 * Otherwise the colors will be on the card_faces objects, see below.
	 */
	private Color[] colors;
	/**
	 * Nullable
	 * This card’s overall rank/popularity on EDHREC. Not all cards are ranked.
	 */
	private Integer edhrecRank;
	/**
	 * True if this printing exists in a foil version.
	 */
	private Boolean foil;
	/**
	 * Nullable
	 * This card’s hand modifier, if it is Vanguard card. This value will contain a delta, such as -1.
	 */
	private String handModifier;
	/**
	 * An array of keywords that this card uses, such as 'Flying' and 'Cumulative upkeep'.
	 */
	private String[] keywords;
	/**
	 * A code for this card’s layout.
	 */
	private String layout;
	/**
	 * An object describing the legality of this card across play formats.
	 * Possible legalities are legal, not_legal, restricted, and banned.
	 */
	private Map<String, Legality> legalities;
	/**
	 * Nullable
	 * This card’s life modifier, if it is Vanguard card. This value will contain a delta, such as +2.
	 */
	private String lifeModifier;
	/**
	 * Nullable
	 * This loyalty if any. Note that some cards have loyalties that are not numeric, such as X.
	 */
	private String loyalty;
	/**
	 * Nullable
	 * The mana cost for this card. This value will be any empty string "" if the cost is absent.
	 * Remember that per the game rules, a missing mana cost and a mana cost of {0} are different values.
	 * Multi-faced cards will report this value in card faces.
	 */
	private String manaCost;
	/**
	 * The name of this card. If this card has multiple faces, this field will contain both names separated by ␣//␣.
	 */
	private String name;
	/**
	 * True if this printing exists in a nonfoil version.
	 */
	private Boolean nonfoil;
	/**
	 * Nullable
	 * The Oracle text for this card, if any.
	 */
	private String oracleText;
	/**
	 * True if this card is oversized.
	 */
	private Boolean oversized;
	/**
	 * Nullable
	 * This card’s power, if any. Note that some cards have powers that are not numeric, such as *.
	 */
	private String power;
	/**
	 * Nullable
	 * Colors of mana that this card could produce.
	 */
	private Color[] producedMana;
	/**
	 * True if this card is on the Reserved List.
	 */
	private Boolean reserved;
	/**
	 * Nullable
	 * This card’s toughness, if any. Note that some cards have toughnesses that are not numeric, such as *.
	 */
	private String toughness;
	/**
	 * The type line of this card.
	 */
	private String typeLine;

	//Print Fields. Cards have the following properties unique to their particular re/print:
	/**
	 * Nullable
	 * The name of the illustrator of this card. Newly spoiled cards may not have this field yet.
	 */
	private String artist;
	/**
	 * Whether this card is found in boosters.
	 */
	private Boolean booster;
	/**
	 * This card’s border color: black, borderless, gold, silver, or white.
	 */
	private String borderColor;
	/**
	 * The Scryfall ID for the card back design present on this card.
	 */
	private UUID cardBackId;
	/**
	 * This card’s collector number. Note that collector numbers can contain non-numeric characters, such as letters or ★.
	 */
	private String collectorNumber;
	/**
	 * Nullable
	 * True if you should consider avoiding use of this print downstream.
	 */
	private Boolean contentWarning;
	/**
	 * True if this card was only released in a video game.
	 */
	private Boolean digital;
	/**
	 * Nullable
	 * The just-for-fun name printed on the card (such as for Godzilla series cards).
	 */
	private String flavorName;
	/**
	 * Nullable
	 * The flavor text, if any.
	 */
	private String flavorText;
	/**
	 * Nullable
	 * This card’s frame effects, if any.
	 */
	private String[] frameEffects;
	/**
	 * This card’s frame layout.
	 */
	private String frame;
	/**
	 * True if this card’s artwork is larger than normal.
	 */
	private Boolean fullArt;
	/**
	 * A list of games that this card print is available in, paper, arena, and/or mtgo.
	 */
	private String[] games;
	/**
	 * True if this card’s imagery is high resolution.
	 */
	private Boolean highresImage;
	/**
	 * Nullable
	 * A unique identifier for the card artwork that remains consistent across reprints.
	 * Newly spoiled cards may not have this field yet.
	 */
	private UUID illustrationId;
	/**
	 * Nullable
	 * An object listing available imagery for this card. See the Card Imagery article for more information.
	 */
	private URI[] imageUris;
	/**
	 * An object containing daily price information for this card, including usd, usd_foil, eur, and tix prices, as strings.
	 */
	private Map<String, Double> prices;
	/**
	 * Nullable
	 * The localized name printed on this card, if any.
	 */
	private String printedName;
	/**
	 * Nullable
	 * The localized text printed on this card, if any.
	 */
	private String printedText;
	/**
	 * Nullable
	 * The localized type line printed on this card, if any.
	 */
	private String printedTypeLine;
	/**
	 * True if this card is a promotional print.
	 */
	private Boolean promo;
	/**
	 * Nullable
	 * An array of strings describing what categories of promo cards this card falls into.
	 */
	private String[] promoTypes;
	/**
	 * An object providing URIs to this card’s listing on major marketplaces.
	 */
	private Object purchaseUris;
	/**
	 * This card’s rarity. One of common, uncommon, rare, or mythic.
	 */
	private String rarity;
	/**
	 * An object providing URIs to this card’s listing on other Magic: The Gathering online resources.
	 */
	private URI[] relatedUris;
	/**
	 * The date this card was first released.
	 */
	private LocalDate releasedAt;
	/**
	 * True if this card is a reprint.
	 */
	private Boolean reprint;
	/**
	 * A link to this card’s set on Scryfall’s website.
	 */
	private URI scryfallSetUri;
	/**
	 * This card’s full set name.
	 */
	private String setName;
	/**
	 * A link to where you can begin paginating this card’s set on the Scryfall API.
	 */
	private URI setSearchUri;
	/**
	 * The type of set this printing is in.
	 */
	private String setType;
	/**
	 * A link to this card’s set object on Scryfall’s API.
	 */
	private URI setUri;
	/**
	 * This card’s set code.
	 */
	private String set;
	/**
	 * True if this card is a Story Spotlight.
	 */
	private Boolean storySpotlight;
	/**
	 * True if the card is printed without text.
	 */
	private Boolean textless;
	/**
	 * Whether this card is a variation of another printing.
	 */
	private Boolean variation;
	/**
	 * Nullable
	 * The printing ID of the printing this card is a variation of.
	 */
	private UUID variationOf;
	/**
	 * Nullable
	 * This card’s watermark, if any.
	 */
	private String watermark;
	/**
	 * Nullable
	 * The date this card was previewed.
	 */
	private LocalDate previewPreviewedAt;
	/**
	 * Nullable
	 * A link to the preview for this card.
	 */
	private URI previewSourceURI;
	/**
	 * Nullable
	 * The name of the source that previewed this card.
	 */
	private String previewSource;


}
