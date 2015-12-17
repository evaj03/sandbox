package Sandbox.PlayingCards;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathanevans on 11/12/2015.
 */
public class Deck {
    public final static int CARDS_IN_SUIT = 13;

    private List<PlayingCard> deck = new ArrayList<PlayingCard>();

    public Deck() {
        generateSuit(PlayingCard.SUIT.CLUBS);
        generateSuit(PlayingCard.SUIT.DIAMONS);
        generateSuit(PlayingCard.SUIT.HEARTS);
        generateSuit(PlayingCard.SUIT.SPADES);
    }


    public void display() {
        for (PlayingCard pc : deck) {
            pc.display();
        }
    }


    public PlayingCard getCard(final PlayingCard.RANK rank, final PlayingCard.SUIT suit) {
        PlayingCard pl = new PlayingCard(rank, suit);

        if (deck.contains(pl)) {
            return pl;
        }

        return null;
    }


    private void generateSuit(final PlayingCard.SUIT suit) {
        for(PlayingCard.RANK r : PlayingCard.RANK.values()) {
            PlayingCard playingCard = new PlayingCard(r, suit);
            deck.add(playingCard);
        }
    }
}
