package war;

import java.util.ArrayList;

public class Game {

    private static Player player1 = new Player();
    private static Player player2 = new Player();

    private static ArrayList<Card> warPile = new ArrayList<>();

    // MAIN METHOD 
    public static void main(String[] args){
    
        Deck deck = new Deck(Game.createDeck());
        
        deck.shuffle();

        // deal cards
        deck.deal(player1, player2);

        System.out.println("----------------------GAME START-----------------------");

        // game start! 
        while (player1.cardsLeft() > 0 && player2.cardsLeft() > 0){
            System.out.println("-----------------------------------------------------------");
            System.out.println("Player 1: " + player1.cardsLeft() + " left -- " + "Player 2: " + player2.cardsLeft() + " left");
            System.out.println();
            Game.flip();
        }

        System.out.println();
        System.out.println("-------------------------GAME END-------------------------");

        if (player1.cardsLeft() == 0){
            System.out.println("PLAYER 2 WINS");
        } else {
            System.out.println("PLAYER 1 WINS");
        }
    }

 // --------------------------- HELPER FUNCTIONS -------------------------------

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

    public static void flip(){
        Card first = player1.flip();
        Card second = player2.flip();

        System.out.println("Player 1 flips " + first.toString() + " -- " + "Player 2 flips " + second.toString());
        warPile.add(first);
        warPile.add(second);

        if (first.getRank().getValue() > second.getRank().getValue()){
            player1.consumeWarPile(warPile);
            System.out.println("PLAYER 1 WINS THE ROUND");

        } else if (first.getRank().getValue() < second.getRank().getValue()){
            System.out.println("PLAYER 2 WINS THE ROUND");
            player2.consumeWarPile(warPile);
        } else if (first.getRank().getValue() == second.getRank().getValue()){
            Game.declareWar();
        }
        warPile.clear();
    }

    // War happens when there are ties. Each player burns 3 cards then go to war with the 4th.
    // if a player does not have enough cards to go to war, they lose. 
    public static void declareWar(){
        System.out.println("TIE! A WAR WILL START ");

        // check that both players have enough cards to declare war. 
        if (player1.cardsLeft() < 5){
            player1.clearCards();
            player2.consumeWarPile(warPile);
            System.out.println();
            System.out.println("PLAYER 1 CANNOT AFFORD A WAR");
            return;
        } else if (player2.cardsLeft() < 5){
            player2.clearCards();
            player1.consumeWarPile(warPile);
            System.out.println();
            System.out.println("PLAYER 2 CANNOT AFFORD A WAR");
            return;
        }

        System.out.println();
        System.out.println("---------- WAR START ----------");

        // add three cards to warPile
        for (int i = 0; i < 3; i++){
            warPile.add(player1.flip());
            warPile.add(player2.flip());
        }


        Game.flip();
        System.out.println("---------- WAR END ------------");
        System.out.println();
    }

}