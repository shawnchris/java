package uber;
enum Suit {
	HEART,
	SPADE,
	CLUB,
	DIAMOND;
}
enum Tax {
    NONE(0), SALES(10), IMPORT(5);

    private final int value;
    private Tax(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

public class Card {
	Suit suit;
	int value;
}
