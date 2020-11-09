package ru.aizen.mtg.scryfall.api.client.query;

public class ScryfallRequestException extends Exception{

	public ScryfallRequestException(String message) {
		super(message);
	}

	public ScryfallRequestException(Throwable cause) {
		super(cause);
	}

}
