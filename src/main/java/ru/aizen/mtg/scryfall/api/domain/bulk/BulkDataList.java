package ru.aizen.mtg.scryfall.api.domain.bulk;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.aizen.mtg.scryfall.api.client.ScryfallObjectType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public final class BulkDataList {

	private final ScryfallObjectType objectType = ScryfallObjectType.LIST;
	private boolean hasMore;
	private List<BulkData> data;

}
