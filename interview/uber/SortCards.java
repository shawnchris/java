package interview.uber;
import java.lang.IllegalArgumentException;
import java.util.Random;

public class SortCards {
	public static void sort(Card[] cards) {
		if (cards == null || cards.length != 52)
			throw new IllegalArgumentException();
		
		for (int i = 0; i < cards.length; i++) {
			while (true) {
				int pos = cards[i].suit.ordinal() * 13 + cards[i].value - 1;
				if (pos == i) {
					break;
				}
				else {
					Card temp = cards[i];
					cards[i] = cards[pos];
					cards[pos] = temp;
				}
			}
		}
	}
	public static void main(String[] args) {
		Card[] cards = new Card[52];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				Card card = new Card();
				if (i == 0)
					card.suit = Suit.HEART;
				else if (i == 1)
					card.suit = Suit.SPADE;
				else if (i == 2)
					card.suit = Suit.CLUB;
				else
					card.suit = Suit.DIAMOND;
				card.value = j + 1;
				cards[i * 13 + j] = card;
			}
		}
		print(cards);
		shuffle(cards);
		print(cards);
		sort(cards);
		print(cards);
	}
	private static void shuffle(Card[] cards) {
		Random rnd = new Random();
		for (int i = 0; i < cards.length - 1; i++) {
			int pos = i + rnd.nextInt(cards.length - i);
			Card temp = cards[i];
			cards[i] = cards[pos];
			cards[pos] = temp;
		}
	}
	private static void print(Card[] cards) {
		for (int i = 0; i < cards.length; i++) {
			System.out.println(cards[i].suit + " " + cards[i].value);
		}
	}

}
