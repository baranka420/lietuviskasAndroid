package com.example.administrator.simpleseekslider;

/**
 * Created by Administrator on 2017.10.25.
 */

public class Player {
    public String playerName;
    public Card playerCards[];
    public Player(String name, Card cards[]){
        this.playerName = name;
        this.playerCards = cards.clone();
    }

    public String getPlayerName() { return playerName; }
    public Card[]getPlayerCards() { return playerCards; }

}
