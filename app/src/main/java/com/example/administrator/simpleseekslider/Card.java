package com.example.administrator.simpleseekslider;

/**
 * Created by Administrator on 2017.10.25.
 */

public class Card {
    public int cardNameID;
    public String cardSuit;

    public Card(int x, String y) {
        this.cardNameID = x;
        this.cardSuit = y;
    }

    public int cardNameID() { return cardNameID(); }
    public String cardSuit() { return cardSuit(); }

    // return a string representation of this point
    public String toString() {
        return "(" + cardNameID() + ", " + cardSuit() + ")";
    }
}
