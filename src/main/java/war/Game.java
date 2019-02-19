package war;

import java.util.ArrayList;

public class Game {

    private Player player1 = new Player();
    private Player player2 = new Player();
    private Deck deck;

    private ArrayList<Card> warPile = new ArrayList<>();

    public Game(Deck deck){
        this.deck = deck;
    }

    public void start(){
        // create a standard deck and shuffle before dealing
        deck.shuffle();

        // deal cards
        deck.deal(player1, player2);

        // game starts
        System.out.println("------------------------GAME START------------------------");
        int i = 0;
        while (player1.cardsLeft() > 0 && player2.cardsLeft() > 0){
            // print score before each round
            System.out.println("-----------------------------------------------------------");
            System.out.println("Player 1: " + player1.cardsLeft() + " left -- " + "Player 2: " + player2.cardsLeft() + " left");
            System.out.println();

            //cut each players cards after 30 rounds to avoid infinitely long games. 
            if (i == 30){
                player1.cutCards();
                player2.cutCards();
                i = 0;
            }

            // flip next card
            flip();
            i++;
        }

        System.out.println();
        System.out.println("-------------------------GAME END-------------------------");

        if (player1.cardsLeft() == 0 && player2.cardsLeft() == 0){
            System.out.println("TIE");
        }else if (player1.cardsLeft() == 0){
            System.out.println("PLAYER 2 WINS");
        } else {
            System.out.println("PLAYER 1 WINS");
        }
    }

 // --------------------------- HELPER FUNCTIONS -------------------------------

    // Each player flips a card, the higher card wins and takes the pile. If its a tie, a war breaks out. 
    public void flip(){
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
            declareWar();
        }
        warPile.clear();
    }

    // War happens when there is a tie. 
    // Each player burns 3 cards then goes to war with the 4th.
    // if a player does not have enough cards to go to war, they lose the game.
    public void declareWar(){
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


        // both players have enough cards so war starts 
        System.out.println();
        System.out.println("---------- WAR START ----------");

        // add three cards to warPile
        for (int i = 0; i < 3; i++){
            warPile.add(player1.flip());
            warPile.add(player2.flip());
        }

        // War with next card.
        flip();
        System.out.println("---------- WAR END ------------");
        System.out.println();
    }

}