package war;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Player {
    private Queue<Card> cards = new ArrayBlockingQueue<Card>(52);

    // player who wins the war takes the warpile
    public void consumeWarPile(ArrayList<Card> warPile){
        for (Card c: warPile){
            cards.add(c);
        }
    }

    public Queue<Card> getCards(){
        return cards;
    }

    public void cutCards(){
        for (int i = 0; i < cards.size()/2; i++){
            addCard(cards.remove());
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