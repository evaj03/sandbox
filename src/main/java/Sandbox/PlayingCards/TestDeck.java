package Sandbox.PlayingCards;

/**
 * Created by jonathanevans on 11/12/2015.
 */
public class TestDeck {
    public static void main(String[] args) {
        Deck deck = new Deck();

        deck.display();

        System.out.println("Has Ace of Spades?: ");
        deck.getCard(PlayingCard.RANK.ACE, PlayingCard.SUIT.SPADES).display();
    }
}
