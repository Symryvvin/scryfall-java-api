package ru.aizen.mtg.scryfall.api.client.query;

import ru.aizen.mtg.scryfall.api.client.ScryfallClient;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkData;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataList;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataType;

import java.io.InputStream;
import java.util.UUID;

public class BulkDataQuery extends ScryfallQueryBuilder {

	public BulkDataQuery(ScryfallClient client) {
		super(client);
	}

	/**
	 * GET /bulk-data
	 *
	 * @return a List of all Bulk Data items on Scryfall.
	 */
	public BulkDataList list() throws ScryfallQueryException, ScryfallRequestException {
		ScryfallRequest<BulkDataList> request = new ScryfallRequest<>(getClient(), method("/bulk-data"), BulkDataList.class);
		return request.execute();
	}

	/**
	 * GET /bulk-data/:type
	 *
	 * @param type The Bulk Data type.
	 * @return a single Bulk Data object with the given type
	 */
	public BulkData byType(BulkDataType type) throws ScryfallQueryException, ScryfallRequestException {
		ScryfallRequest<BulkData> request = new ScryfallRequest<>(getClient(), method("/bulk-data/" + type.getName()), BulkData.class);
		return request.execute();
	}

	public InputStream byTypeAsFile(BulkDataType type) {
		throw new UnsupportedOperationException("Get bulk-data as file not supported");
	}

	/**
	 * GET /bulk-data/:id
	 *
	 * @param id UUID		The id of the Bulk Data object.
	 * @return a single Bulk Data object with the given id (UUID).
	 */
	public BulkData byId(UUID id) throws ScryfallQueryException, ScryfallRequestException {
		ScryfallRequest<BulkData> request = new ScryfallRequest<>(getClient(), method("bulk-data/" + id.toString()), BulkData.class);
		return request.execute();
	}

	public BulkData byIdAsFile(UUID id) {
		throw new UnsupportedOperationException("Get bulk-data as file not supported");
	}

}
