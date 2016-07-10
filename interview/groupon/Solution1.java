package interview.groupon;

public class Solution1 {

}
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


/**

PrintService
  String print()
  
Input Example 1:
  Prize  |  Weight
  A         10 --> 10/60
  B         20 --> 20/60
  C         30 --> 30/60
  
Input Example 2:  
  Give $10    | 2
  Free Burger | 13
  Try Again   | 80
  Free So     | 25
  .
  .
  .
  
If I were print 60 times:
60 --> 10 A's, 20 B's, 30 C's
---> 1/(60!/10!20!30!)

PrintService
  initialize(Input input); // read the input, prepare models and attributes
  Random rand = new Random();
  int i = rand.nextInt(60);
  0~9 -> A
  10~29 (29-10+1) = 20-> B
  ...
  O(K)
  O(logK)
  O(1)
  String print();
  
  while(true) {
    print(); <------ print print print print
  }


  int i;
  // given i, find a range 
  // fundamentally, SEARCH
  
  1,2,2,2,3,3,3,3,3,
  Map
    key  : proportion
    value: pre-generated random numbers

*/

/**
OOP Design.

Blackjack.
Game of 21.

Deck of Cards: 52 cards; 13 each of heart, spade, diamond, clover
Jack, Queen, King: 10
Ace: 1 or 11

1 Dealer
k Players

     D
p1 p2 p3 p4 ...

//
// Sum has to be .,21]
// if sum is (21,), game over for player

// 1. shuffle deck
// 2. everybody gets 2 cards each from the deck
//    but dealer is dealt last
// 3. game starts ---
//    p1: 
//      HIT or PASS based on (player's hand, dealer's 1 public card)
//      HIT: give me another card from the deck
//      PASS: my turn is now over
//    go to next player
//   +dealer takes last turn
//
// 4. (d, p1), (d, p2), ... , (d, pk)
//

class Card {
  enum SUIT {
    SPADES,
      HEARTS,
      DIAMONDS,
      CLUBS,
  }
  public final static int JACK = 10, QUEEN = 10, KING = 10, ACE1 = 1, ACE2 = 11;
  
  private final SUIT suit;
  private final int value;
  
 
}

class Deck {
  private Card[] deck;
  private int position;
  public void shuffle() {}
  public Card deal() {}
}

class Player {
  private List<Card> hand;
  private Strategy strategy;
  
  public Player() {
    
  }
  
  public void addCard(Card card) {}
  
  public void removeCard(Card card) {}
  
  public Card getCard(int pos) {}
  
  public void sortCard() {}
  
  public int getTotalCount() {}
}

class Strategy {
  public int decision(Player p, Player dealter) {}
}
*/