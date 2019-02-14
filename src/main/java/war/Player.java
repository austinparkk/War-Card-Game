package main.java.war;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Player <Card> {
    private Queue<Card> cards = new ArrayBlockingQueue<Card>(52);

    public void consumeWarPile(ArrayList<Card> warPile){
        for (Card c: warPile){
            cards.add(c);
        }
    }

    public Boolean addCard(Card card){
        return cards.add(card);
    }

    public Card flip(){
        return cards.remove();
    }

    public int cardsLeft(){
        return cards.size();
    }

    public void clearCards(){
        cards.clear();
    }

}