package android.example.com;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    CardView basketball, football, volleyball, badminton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basketball = (CardView) findViewById(R.id.basketball);

        basketball.setOnClickListener(v -> {
            Intent bb = new Intent(MainActivity.this, Basketball.class);
            startActivity(bb);
        });

        football = (CardView) findViewById(R.id.football);

        football.setOnClickListener(v -> {
            Intent fb = new Intent(MainActivity.this, Football.class);
            startActivity(fb);
        });

        volleyball = (CardView) findViewById(R.id.volleyball);
        volleyball.setOnClickListener(v -> {
            Intent vb = new Intent(MainActivity.this, Volleyball.class);
            startActivity(vb);
        });

        badminton = (CardView) findViewById(R.id.badminton);

        badminton.setOnClickListener(v -> {
            Intent bd = new Intent(MainActivity.this, Badminton.class);
            startActivity(bd);
        });
    }

}