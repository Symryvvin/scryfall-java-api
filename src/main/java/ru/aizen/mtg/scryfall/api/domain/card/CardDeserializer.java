package ru.aizen.mtg.scryfall.api.domain.card;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.aizen.mtg.scryfall.api.domain.ArrayJsonNode;
import ru.aizen.mtg.scryfall.api.domain.NullableJsonNode;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
		card.setArenaId(new NullableJsonNode<>(Integer.class).parse(node, "arena_id"));
		card.setId(UUID.fromString(node.get("id").asText()));
		card.setLang(new NullableJsonNode<>(String.class).parse(node, "lang"));
		card.setMtgoId(new NullableJsonNode<>(Integer.class).parse(node, "mtgo_id"));
		card.setMtgoFoilId(new NullableJsonNode<>(Integer.class).parse(node, "mtgo_foil_id"));
		card.setMultiverseIds(new ArrayJsonNode<>(Integer.class).parse(node, "multiverse_ids", JsonNode::asInt));
		card.setTcgplayerId(new NullableJsonNode<>(Integer.class).parse(node, "tcgplayer_id"));
		card.setCardmarketId(new NullableJsonNode<>(Integer.class).parse(node, "cardmarket_id"));
		card.setOracleId(UUID.fromString(node.get("oracle_id").asText()));
		card.setPrintsSearchUri((parseURI(node.get("prints_search_uri").asText())));
		card.setRulingsUri((parseURI(node.get("rulings_uri").asText())));
		card.setScryfallUri((parseURI(node.get("scryfall_uri").asText())));
		card.setUri((parseURI(node.get("uri").asText())));
	}

	private void parseGameplayFieldsToCard(Card card, JsonNode node) {
		card.setAllParts(new ArrayJsonNode<>(RelatedCard.class).parse(node, "all_parts", this::parseRelatedCard));
		card.setCardFaces(new ArrayJsonNode<>(CardFace.class).parse(node, "card_faces", this::parseCardFace));
		card.setCmc(node.get("cmc").asInt());
		card.setColorIdentity(new ArrayJsonNode<>(Color.class).parse(node, "color_identity", n -> Color.from(n.asText())));
		card.setColorIndicator(new ArrayJsonNode<>(Color.class).parse(node, "color_indicator", n -> Color.from(n.asText())));
		card.setColors(new ArrayJsonNode<>(Color.class).parse(node, "colors", n -> Color.from(n.asText())));
		card.setEdhrecRank(new NullableJsonNode<>(Integer.class).parse(node, "edhrec_rank"));
		card.setFoil(node.get("foil").asBoolean());
		card.setHandModifier(new NullableJsonNode<>(String.class).parse(node, "hand_modifier"));
		card.setKeywords(new ArrayJsonNode<>(String.class).parse(node, "keywords", JsonNode::textValue));
		card.setLayout(node.get("layout").asText());
		card.setLegalities(parseLegalities(node.get("legalities")));
		card.setLifeModifier(new NullableJsonNode<>(String.class).parse(node, "life_modifier"));
		card.setLoyalty(new NullableJsonNode<>(String.class).parse(node, "loyalty"));
		card.setManaCost(new NullableJsonNode<>(String.class).parse(node, "mana_cost"));
		card.setName(node.get("name").asText());
		card.setNonfoil(node.get("nonfoil").asBoolean());
		card.setOracleText(new NullableJsonNode<>(String.class).parse(node, "oracle_text"));
		card.setOversized(node.get("oversized").asBoolean());
		card.setPower(new NullableJsonNode<>(String.class).parse(node, "power"));
		card.setProducedMana(new ArrayJsonNode<>(Color.class).parse(node, "produced_mana", n -> Color.from(n.asText())));
		card.setReserved(node.get("reserved").asBoolean());
		card.setToughness(new NullableJsonNode<>(String.class).parse(node, "toughness"));
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
		card.setUri(parseURI(node.get("uri").asText()));
		return card;
	}

	private CardFace parseCardFace(JsonNode node) {
		CardFace face = new CardFace();
		face.setArtist(new NullableJsonNode<>(String.class).parse(node, "artist"));
		face.setColor_indicator(new ArrayJsonNode<>(Color.class).parse(node, "color_indicator", n -> Color.from(n.asText())));
		face.setColors(new ArrayJsonNode<>(Color.class).parse(node, "colors", n -> Color.from(n.asText())));
		face.setFlavor_text(new NullableJsonNode<>(String.class).parse(node, "flavor_text"));
		String illustrationId = new NullableJsonNode<>(String.class).parse(node, "illustration_id");
		face.setIllustration_id(illustrationId == null ? null : UUID.fromString(illustrationId));
		face.setImage_uris(new ArrayJsonNode<>(URI.class).parse(node, "image_uris", n -> parseURI(n.asText())));
		face.setLoyalty(new NullableJsonNode<>(String.class).parse(node, "loyalty"));
		face.setMana_cost(node.get("mana_cost").asText());
		face.setName(node.get("name").asText());
		face.setOracle_text(new NullableJsonNode<>(String.class).parse(node, "oracle_text"));
		face.setPower(new NullableJsonNode<>(String.class).parse(node, "power"));
		face.setPrinted_name(new NullableJsonNode<>(String.class).parse(node, "printed_name"));
		face.setPrinted_text(new NullableJsonNode<>(String.class).parse(node, "printed_text"));
		face.setPrinted_type_line(new NullableJsonNode<>(String.class).parse(node, "printed_type_line"));
		face.setToughness(new NullableJsonNode<>(String.class).parse(node, "toughness"));
		face.setType_line(node.get("type_line").asText());
		face.setWatermark(new NullableJsonNode<>(String.class).parse(node, "watermark"));
		return face;
	}

	private void parsePrintFields(Card card, JsonNode node) {
		card.setArtist(new NullableJsonNode<>(String.class).parse(node, "artist"));
		card.setBooster(node.get("booster").asBoolean());
		card.setBorderColor(node.get("border_color").asText());
		card.setCardBackId(UUID.fromString(node.get("card_back_id").asText()));
		card.setCollectorNumber(node.get("collector_number").asText());
		card.setContentWarning(new NullableJsonNode<>(Boolean.class).parse(node, "content_warning"));
		card.setDigital(node.get("digital").asBoolean());
		card.setFlavorName(new NullableJsonNode<>(String.class).parse(node, "flavor_name"));
		card.setFlavorText(new NullableJsonNode<>(String.class).parse(node, "flavor_text"));
		card.setFrameEffects(new ArrayJsonNode<>(String.class).parse(node, "frame_effects", JsonNode::textValue));
		card.setFrame(new NullableJsonNode<>(String.class).parse(node, "frame"));
		card.setFullArt(new NullableJsonNode<>(Boolean.class).parse(node, "full_art"));
		card.setGames(new ArrayJsonNode<>(String.class).parse(node, "games", JsonNode::textValue));
		card.setHighresImage(node.get("highres_image").asBoolean());
		card.setIllustrationId(UUID.fromString(node.get("illustration_id").asText()));
		card.setImageUris(new ArrayJsonNode<>(URI.class).parse(node, "image_uris", n -> parseURI(n.asText())));
		card.setPrices(parsePrices(node.get("prices")));
		card.setPrintedName(new NullableJsonNode<>(String.class).parse(node, "printed_name"));
		card.setPrintedText(new NullableJsonNode<>(String.class).parse(node, "printed_text"));
		card.setPrintedTypeLine(new NullableJsonNode<>(String.class).parse(node, "printed_type_line"));
		card.setPromo(node.get("promo").asBoolean());
		card.setPromoTypes(new ArrayJsonNode<>(String.class).parse(node, "promo_types", JsonNode::textValue));
		card.setPurchaseUris(new ArrayJsonNode<>(URI.class).parse(node, "purchase_uris", n -> parseURI(n.asText())));
		card.setRarity(new NullableJsonNode<>(String.class).parse(node, "rarity"));
		card.setRelatedUris(new ArrayJsonNode<>(URI.class).parse(node, "related_uris", n -> parseURI(n.asText())));
		card.setReleasedAt(LocalDate.parse(node.get("released_at").asText(), formatter));
		card.setReprint(node.get("reprint").asBoolean());
		card.setScryfallSetUri(parseURI(node.get("scryfall_set_uri").asText()));
		card.setSetName(node.get("set_name").asText());
		card.setSetSearchUri(parseURI(node.get("set_search_uri").asText()));
		card.setSetType(node.get("set_type").asText());
		card.setSetUri(parseURI(node.get("set_uri").asText()));
		card.setSet(node.get("set").asText());
		card.setStorySpotlight(node.get("story_spotlight").asBoolean());
		card.setTextless(node.get("textless").asBoolean());
		card.setVariation(node.get("variation").asBoolean());
		String variationOf = new NullableJsonNode<>(String.class).parse(node, "variation_of");
		card.setVariationOf(variationOf == null ? null : UUID.fromString(variationOf));
		card.setWatermark(node.get("watermark").asText());

		String previewAt = new NullableJsonNode<>(String.class).parse(node, "preview.previewed_at");
		card.setPreviewPreviewedAt(previewAt == null ? null : LocalDate.parse(previewAt, formatter));
		String previewSourceURI = new NullableJsonNode<>(String.class).parse(node, "preview.source_uri");
		card.setPreviewSourceURI(previewSourceURI == null ? null : parseURI(previewSourceURI));
		card.setPreviewSource(new NullableJsonNode<>(String.class).parse(node, "preview.source"));
	}

	private Map<String, Double> parsePrices(JsonNode node) {
		Map<String, Double> result = new HashMap<>();
		node.fieldNames()
				.forEachRemaining(fieldName -> result.put(fieldName, node.get(fieldName).asDouble()));
		return result;
	}

	private URI parseURI(String value) {
		try {
			return new URI(value);
		} catch (URISyntaxException e) {
			//log
			return null;
		}
	}

}
