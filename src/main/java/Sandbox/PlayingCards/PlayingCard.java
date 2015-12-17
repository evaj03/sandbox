package Sandbox.PlayingCards;

/**
 * Created by jonathanevans on 11/12/2015.
 */
public class PlayingCard {
    public enum RANK {JOKER, ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING};
    public enum SUIT {CLUBS, DIAMONS, HEARTS, SPADES};

    private RANK rank;
    private SUIT suit;


    public PlayingCard() {

    }


    public PlayingCard(final RANK rank, final SUIT suit) {
        setRank(rank);
        setSuit(suit);
    }


    public void setRank(final RANK rank) {
        this.rank = rank;
    }


    public RANK getRank() {
        return rank;
    }


    public void setSuit(final SUIT suit) {
        this.suit = suit;
    }


    public SUIT getSuit() {
        return suit;
    }


    public void display() {
        System.out.println("Card is '" + rank.name() + "' of '" + suit.name() + "'.");
    }


    // Hacked version
    @Override
    public boolean equals(Object v) {
        boolean returnValue = false;

        if (v instanceof PlayingCard) {
            PlayingCard ptr = (PlayingCard) v;
            returnValue = (ptr.rank == this.rank && ptr.suit == this.suit);
        }

        return returnValue;
    }


    // Hacked version
    public int hashCode() {
        int result = 0;
        result = (int) ((suit.hashCode() + rank.hashCode()) / 11);
        return result;
    }
}
