package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity {

    private static final int HOME_REQUEST_CODE = 1;
    private static final int AWAY_REQUEST_CODE = 2;

    private TextView homeText;
    private TextView awayText;
    private TextView homeTextScore;
    private TextView awayTextScore;
    String homename;
    String awayname;

    private ImageView homeImage;
    private ImageView awayImage;

    private int homeScore;
    private int awayScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",

        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeTextScore = findViewById(R.id.score_home);
        awayTextScore = findViewById(R.id.score_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            homename = extras.getString(MainActivity.HOME_KEY);
            awayname = extras.getString(MainActivity.AWAY_KEY);
            String homeURL = extras.getString(MainActivity.HOMEIMG_KEY);
            String awayURL = extras.getString(MainActivity.AWAYIMG_KEY);
            homeText.setText(homename);
            awayText.setText(awayname);
            try {
                Bitmap homebitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(homeURL));
                Bitmap awaybitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(awayURL));
                homeImage.setImageBitmap(homebitmap);
                awayImage.setImageBitmap(awaybitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void homeScore(View view) {
        Intent intent = new Intent(this,ScorerActivity.class);
        startActivityForResult(intent,1);
    }

    public void awayScore(View view) {
        Intent intent = new Intent(this,ScorerActivity.class);
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == HOME_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                homeScore++;
                homeTextScore.setText(String.valueOf(homeScore));
                // Get String data from Intent
                String returnString = data.getStringExtra("keyName");

                // Set text view with string
                TextView textView = (TextView) findViewById(R.id.scorerHome);
                textView.setText(returnString);
            }
        }
        else if (requestCode == AWAY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                awayScore++;
                awayTextScore.setText(String.valueOf(awayScore));
                // Get String data from Intent
                String returnString = data.getStringExtra("keyName");

                // Set text view with string
                TextView textView = (TextView) findViewById(R.id.scorerAway);
                textView.setText(returnString);
            }
        }
    }

}
