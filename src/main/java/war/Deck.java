package main.java.war;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;


public class Deck <Card> {
    private ArrayList<Card> deck = new ArrayList<>();

    public Deck(ArrayList<Card> deck){
        this.deck = deck;
    }

    public Boolean shuffle(){
        Collections.shuffle(deck);
        return true;
    }

    // deal so that player1 and player2 have the same amount of cards.
    // may be 1 undealt card if the deck has a odd number of cards. 
    public void deal(Player player1, Player player2){
        int i = 0;
        // must be atleast 2 cards left. hence < deck.size()-1
        while (i < deck.size() - 1){
            player1.addCard(deck.get(i));
            player2.addCard(deck.get(i+1));
            
            i = i + 2;
        }
    }
}