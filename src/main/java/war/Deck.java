package main.java.war;

import java.util.Collections;
import java.util.Queue;


public class Deck <Card> {
    private Queue<Card> deck;

    public Deck(Queue<Card> deck){
        this.deck = deck;
    }

    public Card getNextCard(){
        return deck.remove();
    }

    public Boolean addToBottom(Card card){
        return deck.add(card);
    }

    public Boolean isEmpty(){
        return deck.isEmpty();
    }



}