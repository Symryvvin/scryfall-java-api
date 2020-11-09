package ru.aizen.mtg.scryfall.api.parser.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkData;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BulkDataListDeserializer extends JsonDeserializer<BulkDataList> {

	@Override
	public BulkDataList deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException {
		ObjectCodec codec = jsonParser.getCodec();
		JsonNode node = codec.readTree(jsonParser);

		JsonDeserializer<?> dataDeserializer = DeserializerFactory.getDeserializer(BulkData.class);

		JsonNode dataNodes = node.get("data");
		List<BulkData> data = new ArrayList<>();
		for (JsonNode dataNode : dataNodes) {
			data.add((BulkData) dataDeserializer.deserialize(dataNode.traverse(codec), context));
		}

		BulkDataList result = new BulkDataList();
		result.setHasMore(node.get("has_more").booleanValue());
		result.setData(data);

		return result;
	}

}
