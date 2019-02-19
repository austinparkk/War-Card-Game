import org.junit.jupiter.api.*;
import java.util.ArrayList;
import jdk.jfr.Timestamp;
import war.Card;
import war.Deck;
import war.Game;
import war.Player;
import war.Rank;
import war.Suit;

import static org.junit.jupiter.api.Assertions.assertEquals;
 
class GameTests {
    static ArrayList<Card> cards = new ArrayList<Card>();
    Player player1 = new Player();
    Player player2 = new Player();

    @BeforeAll
    static void before(){
        for (int i = 1; i < 5; i++){
            Card heart = new Card(Suit.HEART, Rank.getRank(i));
            Card diamond = new Card(Suit.DIAMOND, Rank.getRank(i));
            Card spade = new Card(Suit.SPADE, Rank.getRank(i));
            Card club = new Card(Suit.CLUB, Rank.getRank(i));

            cards.add(heart);
            cards.add(diamond);
            cards.add(spade);
            cards.add(club);
        }
        cards.add(new Card(Suit.CLUB, Rank.SEVEN));
    }
    @Test
    void testCard() {
        Card card1 = new Card(Suit.CLUB, Rank.ACE);

        assertEquals(1, card1.getRank().getValue());
        assertEquals(Suit.CLUB, card1.getSuit());
    }

    @Test
    void testDeckPlayer(){
        Deck deck = new Deck(cards);
        assertEquals(17, deck.getDeck().size());
        deck.deal(player1, player2);
        assertEquals(8, player1.cardsLeft());
        assertEquals(8, player2.cardsLeft());
        player1.flip();
        assertEquals(7, player1.cardsLeft());
        player1.clearCards();
        deck.deal(player1, player2);
        assertEquals(8, player1.cardsLeft());
        assertEquals(16, player2.cardsLeft());
    }

    @Test
    void testGame(){
        player1.clearCards();
        player2.clearCards();
        Deck deck = new Deck(cards);

        deck.deal(player1, player2); 
        Game game = new Game(deck);
        game.start();
        
        
    }
    
}