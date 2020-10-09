package ru.aizen.mtg.scryfall.api.domain.bulk;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.aizen.mtg.scryfall.api.client.ScryfallObject;
import ru.aizen.mtg.scryfall.api.client.ScryfallObjectType;

import java.net.URI;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BulkData implements ScryfallObject {

	private final ScryfallObjectType objectType = ScryfallObjectType.BULK_DATA;

	@SerializedName("id")
	private UUID id;
	@SerializedName("uri")
	private URI uri;
	@SerializedName("type")
	private String type;
	@SerializedName("updated_at")
	private Timestamp updatedAt;
	@SerializedName("name")
	private String name;
	@SerializedName("description")
	private String description;
	@SerializedName("compressed_size")
	private Long compressedSize;
	@SerializedName("download_uri")
	private URI downloadUri;
	@SerializedName("content_type")
	private String contentType;
	@SerializedName("content_encoding")
	private String contentEncoding;


}
