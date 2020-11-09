package ru.aizen.mtg.scryfall.api.domain.card;

public class Color {

	public static final Color WHITE = new Color('W', "White", "Plains");
	public static final Color BLUE = new Color('U', "Blue", "Island");
	public static final Color BLACK = new Color('B', "Black", "Swamp");
	public static final Color RED = new Color('R', "Red", "Mountain");
	public static final Color GREEN = new Color('G', "Green", "Forest");

	private final char abbr;
	private final String color;
	private final String basicLand;

	private Color(char abbr, String color, String basicLand) {
		this.abbr = abbr;
		this.color = color;
		this.basicLand = basicLand;
	}

	public static Color from(String value) {
		switch (value) {
			case "W":
				return WHITE;
			case "U":
				return BLUE;
			case "B":
				return BLACK;
			case "R":
				return RED;
			case "G":
				return GREEN;
			default:
				return null;
		}
	}

	public char getAbbr() {
		return abbr;
	}

	public String getColor() {
		return color;
	}

	public String getBasicLand() {
		return basicLand;
	}

	@Override
	public String toString() {
		return "Color{" +
				"abbr=" + abbr +
				", color='" + color + '\'' +
				", basicLand='" + basicLand + '\'' +
				'}';
	}

}




