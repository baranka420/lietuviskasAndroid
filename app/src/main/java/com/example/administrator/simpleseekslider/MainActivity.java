package com.example.administrator.simpleseekslider;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    TextView displayPlayerCount;
    SeekBar seekBar;
    EditText playerCountInput;
    Button confirmPlayerCountButton;
    TextView displayCardSuits;
    TextView playerNameInput;
    TextView displayPlayerName;
    Button confirmNameButton;
    int minSuitsId = 0;
    int maxSuitsId = 3;
    int minId = 9;
    int maxId = 14;
    int playerCount;
    int cardCount = 4;
    String playerName = null;
    Card []cards = new Card[cardCount];
    Player player1;
    Player[] players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayPlayerCount = (TextView) findViewById(R.id.displayPlayerCount);
        displayCardSuits = (TextView) findViewById(R.id.displayCardSuits);
        displayPlayerName = (TextView) findViewById(R.id.displayPlayerName);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        playerCountInput = (EditText) findViewById(R.id.playerCountInput);
        playerNameInput = (EditText) findViewById(R.id.playerNameInput);
        confirmPlayerCountButton = (Button) findViewById(R.id.confirmButton);
        confirmNameButton = (Button) findViewById(R.id.confirmNameButton);
        playerCount = 0;
        playerCountInput.setText("enter number of players", TextView.BufferType.EDITABLE);
        playerNameInput.setText("enter player name", TextView.BufferType.EDITABLE);
        confirmPlayerCountButton.setOnClickListener(new AddPlayerCount());
        confirmNameButton.setOnClickListener(new AddPlayerName());
        for(int x = 0; x < cardCount; x++){
            int cardId = minId + (int)(Math.random() * ((maxId - minId) + 1));
            int cardSuitsId = minSuitsId + (int)(Math.random() * ((maxSuitsId - minSuitsId) + 1));
            cards[x] = new Card(cardId, giveSuitsId(cardSuitsId));
        }
        player1 = new Player("asshole", cards, cardCount);
        System.out.println(player1.getPlayerName());
    }
    protected String giveSuitsId(int id){
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
    private class AddPlayerCount implements Button.OnClickListener{
        @Override
        public void onClick(View v) {
            if(playerName != null) {
                String checkInt = playerCountInput.getText().toString();
                if (isNumeric(checkInt)) {
                    playerCount = Integer.parseInt(checkInt);
                    players = new Player[playerCount];
                    for (int x = 0; x < playerCount; x++) {
                        players[x] = new Player(playerName, cards, cardCount);
                        for (int y = 0; y < 1; y++) {
                            players[x].playerCards[y].cardSuit = giveSuitsId(minSuitsId + (int) (Math.random() * ((maxSuitsId - minSuitsId) + 1)));
                            players[x].playerCards[y].cardNameID = minId + (int) (Math.random() * ((maxId - minId) + 1));
                        }
                    }
                } else {
                    playerCount = 0;
                }
                displayPlayerCount.setText(Integer.toString(playerCount));
                Card[] cardz = player1.getPlayerCards().clone();
                displayCardSuits.setText(cardz[1].cardSuit);
                //displayCardSuits.setText(players[0].getPlayerName());
            }
        }
    }
    private class AddPlayerName implements Button.OnClickListener{
        @Override
        public void onClick(View v) {
            playerName = playerNameInput.getText().toString();
        }
    }
    public static boolean isNumeric(String str){
        try{
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

}





