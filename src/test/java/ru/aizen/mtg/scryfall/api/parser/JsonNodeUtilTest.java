package ru.aizen.mtg.scryfall.api.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JsonNodeUtilTest {

	@Test
	void parseSimpleNode() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree("{\"string\": \"this is a text\", \"integer\": \"10\", \"double\": \"11.32\", \"boolean\": \"true\"}");

		assertEquals("this is a text", JsonNodeUtil.parseSimpleNode(node, "string", String.class));
		assertEquals(10, JsonNodeUtil.parseSimpleNode(node, "integer", Integer.class));
		assertEquals(11.32d, JsonNodeUtil.parseSimpleNode(node, "double", Double.class));
		assertEquals(true, JsonNodeUtil.parseSimpleNode(node, "boolean", Boolean.class));
		assertNull(JsonNodeUtil.parseSimpleNode(node, "no_field", Object.class));
	}

	@Test
	void parse() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree("{\"array\": [\"value1\", \"value2\"]}");

		String[] array = JsonNodeUtil.parseArrayNode(node, "array", String.class, JsonNode::asText);

		assertEquals("value1", array[0]);
		assertEquals("value2", array[1]);
	}

	@Test
	void parseUUID() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree("{\"id\": \"00000000-0000-0000-0000-000000000000\"}");

		UUID id = JsonNodeUtil.parseUUID(node, "id");

		assertNotNull(id);
		assertEquals("00000000-0000-0000-0000-000000000000", id.toString());
	}

}