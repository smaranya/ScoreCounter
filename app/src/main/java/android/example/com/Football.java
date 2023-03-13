package android.example.com;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class Football extends AppCompatActivity {

    Button homeBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football2);
        homeScreen();
    }
    public void homeScreen(){
        homeBt = findViewById(R.id.previous_buttonfromfb);
        homeBt.setOnClickListener(v -> {
            onHome();
        });
    }

    Button timerBt, timerStop;
    TextView timerText;
    CountDownTimer timeCtrl = null;

    public void Timer(View v) {
        timerBt = (Button) findViewById(R.id.timerBt);
        timerStop = (Button) findViewById(R.id.timerStop);
        timerText = (TextView) findViewById(R.id.timerText);

            timeCtrl = new CountDownTimer(2700000, 1000) {
                public void onTick(long millisUntilFinished) {
                    timerText.setText("" + String.format("%d:%d",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {
                    timerText.setText("45:00");
                }
            }.start();
    }

    public void TimerStop(View v){
        timeCtrl.cancel();
    }

    int scoreTeamA = 0;
    int scoreTeamB = 0;

    public void resetScore(View v){
        scoreTeamA = scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displayWinner(scoreTeamA, scoreTeamB);
        timeCtrl.cancel();
        timerText.setText("45:00");
    }
    /**
     * Increase the score for Team A by 1 point.
     */
    public void addGoalForTeamA(View v) {
        scoreTeamA = scoreTeamA + 1;
        displayForTeamA(scoreTeamA);
        displayWinner(scoreTeamA, scoreTeamB);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int scoreTeamA) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(scoreTeamA));
    }

    public void addGoalForTeamB(View v) {
        scoreTeamB = scoreTeamB + 1;
        displayForTeamB(scoreTeamB);
        displayWinner(scoreTeamA, scoreTeamB);
    }

    public void displayForTeamB(int scoreTeamB) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(scoreTeamB));
    }

    public void displayWinner(int scoreTeamA, int scoreTeamB){
        TextView result = (TextView) findViewById(R.id.winner);

        if(scoreTeamA == 0 && scoreTeamB == 0){
            result.setText("No score yet!");
        }
        else if(scoreTeamA > scoreTeamB){
            result.setText("Team A is winning");
        }
        else if(scoreTeamB > scoreTeamA){
            result.setText("Team B is winning");
        }
        else{
            result.setText("Inching to a Tie");
        }
    }

    public void onHome(){
        AlertDialog.Builder home = new AlertDialog.Builder(Football.this);
        if(scoreTeamA == 0 && scoreTeamB == 0){
            home.setMessage("You haven't played yet!\nDo you want to go back?");
            home.setTitle("Home");
            home.setCancelable(false);
        }
        else if(scoreTeamA > scoreTeamB){
            home.setMessage("Team A won!\nDo you want to go back?");
            home.setTitle("Home");
            home.setCancelable(false);
        }
        else if(scoreTeamB > scoreTeamA){
            home.setMessage("Team B won!\nDo you want to go back?");
            home.setTitle("Home");
            home.setCancelable(false);
        }
        else{
            home.setMessage("It was a Tie!\nDo you want to go back?");
            home.setTitle("Home");
            home.setCancelable(false);
        }

        home.setPositiveButton("Yes", (DialogInterface.OnClickListener)(dialog, which) -> {
            Intent back = new Intent(Football.this, MainActivity.class);
            startActivity(back);
        });

        home.setNegativeButton("No", (DialogInterface.OnClickListener)(dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = home.create();
        alertDialog.show();
    }
}