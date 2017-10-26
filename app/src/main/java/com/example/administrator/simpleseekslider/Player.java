package com.example.administrator.simpleseekslider;

/**
 * Created by Administrator on 2017.10.25.
 */

public class Player {
    public String playerName;
    public Card []playerCards;
    public int cardCount;
    public Player(String name, Card cards[], int number){
        this.playerName = name;
        this.playerCards = cards.clone();
        this.cardCount = number;
    }

    public String getPlayerName() { return this.playerName; }
    public Card[]getPlayerCards() { return this.playerCards; }
    public int getCardCount() { return this.cardCount; }

}
