package ru.aizen.mtg.scryfall.api.domain.bulk;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BulkDataListDeserializer extends StdDeserializer<BulkDataList> {

	public BulkDataListDeserializer() {
		this(null);
	}

	protected BulkDataListDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public BulkDataList deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException {
		ObjectCodec codec = jsonParser.getCodec();
		JsonNode node = codec.readTree(jsonParser);
		JsonDeserializer<Object> dataDeserializer = context.findNonContextualValueDeserializer(context.constructType(BulkData.class));

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
