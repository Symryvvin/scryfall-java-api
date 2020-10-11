package ru.aizen.mtg.scryfall.api.domain.bulk;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.aizen.mtg.scryfall.api.client.ScryfallObject;
import ru.aizen.mtg.scryfall.api.client.ScryfallObjectType;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BulkData implements ScryfallObject {

	private final ScryfallObjectType objectType = ScryfallObjectType.BULK_DATA;

	private UUID id;
	private URI uri;
	private String type;
	private LocalDateTime updatedAt;
	private String name;
	private String description;
	private Long compressedSize;
	private URI downloadUri;
	private String contentType;
	private String contentEncoding;

}
