package ru.aizen.mtg.scryfall.api.domain.bulk;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class BulkDataDeserializer extends StdDeserializer<BulkData> {

	@SuppressWarnings("SpellCheckingInspection")
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

	public BulkDataDeserializer() {
		this(BulkData.class);
	}

	protected BulkDataDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public BulkData deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException {
		BulkData result = new BulkData();
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);

		try {
			result.setId(UUID.fromString(node.get("id").asText()));
			result.setUri(new URI(node.get("uri").asText()));
			result.setType(node.get("type").asText());
			result.setUpdatedAt(LocalDateTime.parse(node.get("updated_at").asText(), formatter));
			result.setName(node.get("name").asText());
			result.setDescription(node.get("description").asText());
			result.setCompressedSize(node.get("compressed_size").asLong());
			result.setDownloadUri(new URI(node.get("download_uri").asText()));
			result.setContentType(node.get("content_type").asText());
			result.setContentEncoding(node.get("content_encoding").asText());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return result;
	}


}
