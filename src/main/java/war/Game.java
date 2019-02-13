package main.java.war;

import java.util.Queue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;

public class Game {
    public static void main(String[] args){
    
        ArrayList<Card> cards = new ArrayList<Card>(52);
        Queue<Card> player1 = new ArrayBlockingQueue<Card>(52);
        Queue<Card> player2 = new ArrayBlockingQueue<Card>(52);

        // create the deck
        for (int i = 1; i < 14; i++){
            Card heart = new Card(Suit.HEART, Rank.getRank(i));
            Card diamond = new Card(Suit.DIAMOND, Rank.getRank(i));
            Card spade = new Card(Suit.SPADE, Rank.getRank(i));
            Card club = new Card(Suit.CLUB, Rank.getRank(i));

            cards.add(heart);
            cards.add(diamond);
            cards.add(spade);
            cards.add(club);
        }

        Collections.shuffle(cards);

        Queue<Card> cardsQueue = new ArrayBlockingQueue<Card>(52);

        for (Card c: cards){
            cardsQueue.add(c);
        }

        Deck<Card> deck = new Deck(cardsQueue);
        
        // deal cards
        while (!deck.isEmpty()){
            player1.add(deck.getNextCard());
            player2.add(deck.getNextCard());
        }

        Deck<Card> p1_deck = new Deck(player1);
        Deck<Card> p2_deck = new Deck(player2);

        while (!p1_deck.isEmpty()){
            Card c = p1_deck.getNextCard();
            Card c2 = p2_deck.getNextCard();

            System.out.println(c.getRank() + " of " + c.getSuit() + " vs " + c2.getRank() + " of " + c2.getSuit());

        }
    }
}