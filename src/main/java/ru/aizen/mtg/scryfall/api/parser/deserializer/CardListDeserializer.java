package ru.aizen.mtg.scryfall.api.parser.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.aizen.mtg.scryfall.api.domain.card.Card;
import ru.aizen.mtg.scryfall.api.domain.card.CardList;
import ru.aizen.mtg.scryfall.api.parser.JsonNodeUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardListDeserializer extends StdDeserializer<CardList> {

	public CardListDeserializer() {
		this(null);
	}

	protected CardListDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public CardList deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
		ObjectCodec codec = jsonParser.getCodec();
		JsonNode node = codec.readTree(jsonParser);
		JsonDeserializer<Object> dataDeserializer = context.findNonContextualValueDeserializer(context.constructType(Card.class));

		JsonNode dataNodes = node.get("data");
		List<Card> data = new ArrayList<>();
		for (JsonNode dataNode : dataNodes) {
			data.add((Card) dataDeserializer.deserialize(dataNode.traverse(codec), context));
		}

		CardList result = new CardList();
		result.setHasMore(node.get("has_more").booleanValue());
		result.setTotalCards(node.get("total_cards").asInt());
		result.setNextPage(JsonNodeUtil.parseURI(node, "next_page"));
		result.setData(data);

		return result;
	}

}
