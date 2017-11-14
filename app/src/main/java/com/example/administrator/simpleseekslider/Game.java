package com.example.administrator.simpleseekslider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.TextView;

public class Game extends AppCompatActivity {
    Button takeTurnButton;
    Spinner pickCombinationSpinner;
    TextView firstCard, nameDisplay;
    ImageView iv1, iv2, iv3, iv4;
    private ImageView[] images = {iv1, iv2, iv3, iv4};
    final int minSuitsId = 0;
    final int maxSuitsId = 3;
    final int minId = 9;
    final int maxId = 14;
    int playerCount;
    int cardCount = 1;
    int playerTurn = 0;
    String playerNames;
    Card[] cards = new Card[cardCount];
    Player[] players;
    String[] playerNamesArray;
    AllCards[] allCards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        takeTurnButton = (Button) findViewById(R.id.takeTurnButton);
        pickCombinationSpinner = (Spinner) findViewById(R.id.pickCombinationSpinner);
        firstCard = (TextView) findViewById(R.id.firstCard);
        nameDisplay = (TextView) findViewById(R.id.nameDisplay);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        Context context = this.getApplicationContext();
        Intent gameDataIntent = getIntent();
        playerCount = gameDataIntent.getExtras().getInt("playerCount");
        //Intent playerNamesIntent = getIntent();
        playerNames = gameDataIntent.getExtras().getString("playerNames");
        playerNamesArray = playerNames.split(",");
        allCards = new AllCards[24];
        players = new Player[playerCount];
        for(int x = 0; x < cardCount; x++){
            //int cardId = minId + (int)(Math.random() * ((maxId - minId) + 1));
            //int cardSuitsId = minSuitsId + (int)(Math.random() * ((maxSuitsId - minSuitsId) + 1));
            cards[x] = new Card(minId, giveSuitsNameById(minSuitsId));
        }
        int i = 0;
        for (int x = minId; x < maxId+1; x++) {
            for(int y = minSuitsId; y < maxSuitsId+1; y++){
                allCards[i] = new AllCards(x, giveSuitsNameById(y));
                i++;
            }
        }
        for(int x = 0; x < playerCount; x++) {
            players[x] = new Player(playerNamesArray[x], cards, cardCount);
            dealCards(x);
        }
        showPlayerView(playerTurn, context);
    }

    public void dealCards(int playerNumber){
        for (int y = 0; y < players[playerNumber].getCardCount(); y++) {
            players[playerNumber].playerCards[y].cardSuit = giveSuitsNameById(minSuitsId + (int) (Math.random() * ((maxSuitsId - minSuitsId) + 1)));
            players[playerNumber].playerCards[y].cardNameID = minId + (int) (Math.random() * ((maxId - minId) + 1));
            for(int x = 0; x < allCards.length; x++){
                if(allCards[x].cardId == players[playerNumber].playerCards[y].cardNameID && allCards[x].cardSuit == players[playerNumber].playerCards[y].cardSuit && allCards[x].occupied()){
                    y-=1;
                }
            }
        }
    }

    protected String giveSuitsNameById(int id){
        String suitsId;
        switch (id){
            case 0:
                suitsId = "diamonds";
                break;
            case 1:
                suitsId = "spades";
                break;
            case 2:
                suitsId = "hearts";
                break;
            case 3:
                suitsId = "clubs";
                break;
            default:
                suitsId = "wtf is not integer";
                break;
        }
        return suitsId;
    }

    public void showPlayerView(int playerTurn, Context ctx){
        firstCard.setText(players[playerTurn].playerCards[0].cardSuit + players[playerTurn].playerCards[0].cardNameID);
        nameDisplay.setText(players[playerTurn].getPlayerName());
        for(int x = 0; x < players[playerTurn].cardCount; x++){
            //String a = "drawable://" + "a" + Integer.toString(players[playerTurn].playerCards[x].cardNameID) + "_of_" + players[playerTurn].playerCards[x].cardSuit;

            String name = "a" + Integer.toString(players[playerTurn].playerCards[x].cardNameID) + "_of_" + players[playerTurn].playerCards[x].cardSuit;
            int id = getResources().getIdentifier(name, "drawable", getPackageName());
            Drawable drawale = getResources().getDrawable(id);
            //images[x].setImageDrawable(ContextCompat.getDrawable(ctx , id));
            images[x].setImageDrawable(drawale);
            //images[x].setImageDrawable(drawale);
        }
    }
}
