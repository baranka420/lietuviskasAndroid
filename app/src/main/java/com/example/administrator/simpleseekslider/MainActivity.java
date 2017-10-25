package com.example.administrator.simpleseekslider;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    TextView textview;
    SeekBar seekBar;
    EditText playerCountInput;
    Button confirmPlayerCountButton;
    int playerCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView) findViewById(R.id.textView);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        playerCountInput = (EditText) findViewById(R.id.editText);
        confirmPlayerCountButton = (Button) findViewById(R.id.confirmButton);
        playerCount = 0;
        int cardCount = 3;
        playerCountInput.setText("enter number of players", TextView.BufferType.EDITABLE);
        confirmPlayerCountButton.setOnClickListener(new AddPlayerCount());
        Card []cards = new Card[cardCount];
        for(int x = 0; x < cardCount; x++){
            cards[x] = new Card(x, "spades");
        }
        Player player1 = new Player("asshole", cards);
        System.out.println(player1.getPlayerName());
        System.out.println(player1.getPlayerCards());
    }
    private class AddPlayerCount implements Button.OnClickListener{
        @Override
        public void onClick(View v) {
            playerCount = Integer.parseInt(playerCountInput.getText().toString());
            textview.setText(Integer.toString(playerCount));
        }
    }
    
}





