package war;
import java.util.ArrayList;
public class App{


    public static void main(String[] args){
        Game game = new Game(new Deck(createDeck()));
        game.start();
    }


// ------------- HELPER FUNCTIONS --------------------------
     // creates a standard deck of 52 cards
     public static ArrayList<Card> createDeck(){
        ArrayList<Card> standardDeck = new ArrayList<Card>(52);
    
        for (int i = 1; i < 14; i++){
            Card heart = new Card(Suit.HEART, Rank.getRank(i));
            Card diamond = new Card(Suit.DIAMOND, Rank.getRank(i));
            Card spade = new Card(Suit.SPADE, Rank.getRank(i));
            Card club = new Card(Suit.CLUB, Rank.getRank(i));

            standardDeck.add(heart);
            standardDeck.add(diamond);
            standardDeck.add(spade);
            standardDeck.add(club);
        }

        return standardDeck;
    }
}