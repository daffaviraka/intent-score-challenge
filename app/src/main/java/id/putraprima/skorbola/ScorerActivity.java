package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ScorerActivity extends AppCompatActivity {

    private EditText scorer;
    private EditText minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        
    }
    // "Send text back" button click
    public void handleSubmitGoal(View view) {

        // Get the text from the EditText
        //goal scorer
        EditText editText = (EditText) findViewById(R.id.editText);
        String stringToPassBack = editText.getText().toString();
        //minutes
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String stringToPassBack2 = editText2.getText().toString();

        // Put the String to pass back into an Intent and close this activity
        Intent intent = new Intent();
        intent.putExtra("keyName", stringToPassBack);
        intent.putExtra("keyName2", stringToPassBack2);
        setResult(RESULT_OK, intent);
        finish();
    }
}
