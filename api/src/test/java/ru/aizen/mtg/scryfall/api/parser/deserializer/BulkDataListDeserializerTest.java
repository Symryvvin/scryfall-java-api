package ru.aizen.mtg.scryfall.api.parser.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Test;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkData;
import ru.aizen.mtg.scryfall.api.domain.bulk.BulkDataList;
import ru.aizen.mtg.scryfall.api.domain.list.ScryfallList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

class BulkDataListDeserializerTest {

	@Test
	void deserialize() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();

		module.addDeserializer(BulkDataList.class, new BulkDataListDeserializer());
		module.addDeserializer(BulkData.class, new BulkDataDeserializer());
		mapper.registerModule(module);

		InputStream inputStream = getClass().getResourceAsStream("/bulk-data-list.json");

		String json = new BufferedReader(
				new InputStreamReader(inputStream, StandardCharsets.UTF_8))
				.lines()
				.collect(Collectors.joining("\n"));

		ScryfallList<BulkData> bulkDataList = mapper.readValue(json, BulkDataList.class);
		System.out.println(bulkDataList);
	}

}