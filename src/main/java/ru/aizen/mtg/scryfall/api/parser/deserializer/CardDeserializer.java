package ru.aizen.mtg.scryfall.api.parser.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.aizen.mtg.scryfall.api.domain.card.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static ru.aizen.mtg.scryfall.api.parser.JsonNodeUtil.*;

public class CardDeserializer extends StdDeserializer<Card> {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public CardDeserializer() {
		this(null);
	}

	protected CardDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Card deserialize(JsonParser parser, DeserializationContext context) throws IOException {
		Card card = new Card();
		JsonNode node = parser.getCodec().readTree(parser);

		parseCoreFieldsToCard(card, node);
		parseGameplayFieldsToCard(card, node);
		parsePrintFields(card, node);

		return card;
	}

	private void parseCoreFieldsToCard(Card card, JsonNode node) {
		card.setArenaId(parseSimpleNode(node, "arena_id", Integer.class));
		card.setId(parseUUID(node, "id"));
		card.setLang(parseSimpleNode(node, "lang", String.class));
		card.setMtgoId(parseSimpleNode(node, "mtgo_id", Integer.class));
		card.setMtgoFoilId(parseSimpleNode(node, "mtgo_foil_id", Integer.class));
		card.setMultiverseIds(parseArrayNode(node, "multiverse_ids", Integer.class, JsonNode::asInt));
		card.setTcgplayerId(parseSimpleNode(node, "tcgplayer_id", Integer.class));
		card.setCardmarketId(parseSimpleNode(node, "cardmarket_id", Integer.class));
		card.setOracleId(parseUUID(node, "oracle_id"));
		card.setPrintsSearchUri((parseURI(node, "prints_search_uri")));
		card.setRulingsUri((parseURI(node, "rulings_uri")));
		card.setScryfallUri((parseURI(node, "scryfall_uri")));
		card.setUri((parseURI(node, "uri")));
	}

	private void parseGameplayFieldsToCard(Card card, JsonNode node) {
		card.setAllParts(parseArrayNode(node, "all_parts", RelatedCard.class, this::parseRelatedCard));
		card.setCardFaces(parseArrayNode(node, "card_faces", CardFace.class, this::parseCardFace));
		card.setCmc(node.get("cmc").asInt());
		card.setColorIdentity(parseArrayNode(node, "color_identity", Color.class, n -> Color.from(n.asText())));
		card.setColorIndicator(parseArrayNode(node, "color_indicator", Color.class, n -> Color.from(n.asText())));
		card.setColors(parseArrayNode(node, "colors", Color.class, n -> Color.from(n.asText())));
		card.setEdhrecRank(parseSimpleNode(node, "edhrec_rank", Integer.class));
		card.setFoil(node.get("foil").asBoolean());
		card.setHandModifier(parseSimpleNode(node, "hand_modifier", String.class));
		card.setKeywords(parseArrayNode(node, "keywords", String.class, JsonNode::textValue));
		card.setLayout(node.get("layout").asText());
		card.setLegalities(parseLegalities(node.get("legalities")));
		card.setLifeModifier(parseSimpleNode(node, "life_modifier", String.class));
		card.setLoyalty(parseSimpleNode(node, "loyalty", String.class));
		card.setManaCost(parseSimpleNode(node, "mana_cost", String.class));
		card.setName(node.get("name").asText());
		card.setNonfoil(node.get("nonfoil").asBoolean());
		card.setOracleText(parseSimpleNode(node, "oracle_text", String.class));
		card.setOversized(node.get("oversized").asBoolean());
		card.setPower(parseSimpleNode(node, "power", String.class));
		card.setProducedMana(parseArrayNode(node, "produced_mana", Color.class, n -> Color.from(n.asText())));
		card.setReserved(node.get("reserved").asBoolean());
		card.setToughness(parseSimpleNode(node, "toughness", String.class));
		card.setTypeLine(node.get("type_line").asText());
	}

	private Map<String, Legality> parseLegalities(JsonNode node) {
		Map<String, Legality> result = new HashMap<>();
		node.fieldNames()
				.forEachRemaining(fieldName -> result.put(fieldName, Legality.valueOf(node.get(fieldName).asText().toUpperCase())));
		return result;
	}

	private RelatedCard parseRelatedCard(JsonNode node) {
		RelatedCard card = new RelatedCard();
		card.setId(UUID.fromString(node.get("id").asText()));
		card.setComponent(node.get("component").asText());
		card.setName(node.get("name").asText());
		card.setTypeLine(node.get("type_line").asText());
		card.setUri(parseURI(node, "uri"));
		return card;
	}

	private CardFace parseCardFace(JsonNode node) {
		CardFace face = new CardFace();
		face.setArtist(parseSimpleNode(node, "artist", String.class));
		face.setColor_indicator(parseArrayNode(node, "color_indicator", Color.class, n -> Color.from(n.asText())));
		face.setColors(parseArrayNode(node, "colors", Color.class, n -> Color.from(n.asText())));
		face.setFlavor_text(parseSimpleNode(node, "flavor_text", String.class));
		face.setIllustration_id(parseUUID(node, "illustration_id"));
		face.setImage_uris(parseArrayNode(node, "image_uris", URI.class, this::parseURI));
		face.setLoyalty(parseSimpleNode(node, "loyalty", String.class));
		face.setMana_cost(node.get("mana_cost").asText());
		face.setName(node.get("name").asText());
		face.setOracle_text(parseSimpleNode(node, "oracle_text", String.class));
		face.setPower(parseSimpleNode(node, "power", String.class));
		face.setPrinted_name(parseSimpleNode(node, "printed_name", String.class));
		face.setPrinted_text(parseSimpleNode(node, "printed_text", String.class));
		face.setPrinted_type_line(parseSimpleNode(node, "printed_type_line", String.class));
		face.setToughness(parseSimpleNode(node, "toughness", String.class));
		face.setType_line(node.get("type_line").asText());
		face.setWatermark(parseSimpleNode(node, "watermark", String.class));
		return face;
	}

	private void parsePrintFields(Card card, JsonNode node) {
		card.setArtist(parseSimpleNode(node, "artist", String.class));
		card.setBooster(node.get("booster").asBoolean());
		card.setBorderColor(node.get("border_color").asText());
		card.setCardBackId(UUID.fromString(node.get("card_back_id").asText()));
		card.setCollectorNumber(node.get("collector_number").asText());
		card.setContentWarning(parseSimpleNode(node, "content_warning", Boolean.class));
		card.setDigital(node.get("digital").asBoolean());
		card.setFlavorName(parseSimpleNode(node, "flavor_name", String.class));
		card.setFlavorText(parseSimpleNode(node, "flavor_text", String.class));
		card.setFrameEffects(parseArrayNode(node, "frame_effects", String.class, JsonNode::textValue));
		card.setFrame(parseSimpleNode(node, "frame", String.class));
		card.setFullArt(parseSimpleNode(node, "full_art", Boolean.class));
		card.setGames(parseArrayNode(node, "games", String.class, JsonNode::textValue));
		card.setHighresImage(node.get("highres_image").asBoolean());
		card.setIllustrationId(UUID.fromString(node.get("illustration_id").asText()));
		card.setImageUris(parseArrayNode(node, "image_uris", URI.class, this::parseURI));
		card.setPrices(parsePrices(node.get("prices")));
		card.setPrintedName(parseSimpleNode(node, "printed_name", String.class));
		card.setPrintedText(parseSimpleNode(node, "printed_text", String.class));
		card.setPrintedTypeLine(parseSimpleNode(node, "printed_type_line", String.class));
		card.setPromo(node.get("promo").asBoolean());
		card.setPromoTypes(parseArrayNode(node, "promo_types", String.class, JsonNode::textValue));
		card.setPurchaseUris(parseArrayNode(node, "purchase_uris", URI.class, this::parseURI));
		card.setRarity(parseSimpleNode(node, "rarity", String.class));
		card.setRelatedUris(parseArrayNode(node, "related_uris", URI.class, this::parseURI));
		card.setReleasedAt(LocalDate.parse(node.get("released_at").asText(), formatter));
		card.setReprint(node.get("reprint").asBoolean());
		card.setScryfallSetUri(parseURI(node, "scryfall_set_uri"));
		card.setSetName(node.get("set_name").asText());
		card.setSetSearchUri(parseURI(node, "set_search_uri"));
		card.setSetType(node.get("set_type").asText());
		card.setSetUri(parseURI(node, "set_uri"));
		card.setSet(node.get("set").asText());
		card.setStorySpotlight(node.get("story_spotlight").asBoolean());
		card.setTextless(node.get("textless").asBoolean());
		card.setVariation(node.get("variation").asBoolean());
		card.setVariationOf(parseUUID(node, "variation_of"));
		card.setWatermark(parseSimpleNode(node, "watermark", String.class));
		card.setPreviewPreviewedAt(parseLocalDate(node, "preview.previewed_at", formatter));
		card.setPreviewSourceURI(parseURI(node, "preview.source_uri"));
		card.setPreviewSource(parseSimpleNode(node, "preview.source", String.class));
	}

	private Map<String, Double> parsePrices(JsonNode node) {
		Map<String, Double> result = new HashMap<>();
		node.fieldNames()
				.forEachRemaining(fieldName -> result.put(fieldName, node.get(fieldName).asDouble()));
		return result;
	}

	private URI parseURI(JsonNode node, String fieldName) {
		return node.has(fieldName) ? parseURI(node.get(fieldName)) : null;
	}

	private URI parseURI(JsonNode node) {
		try {
			return new URI(node.asText());
		} catch (URISyntaxException e) {
			//log
			e.printStackTrace();
		}
		return null;
	}

}
