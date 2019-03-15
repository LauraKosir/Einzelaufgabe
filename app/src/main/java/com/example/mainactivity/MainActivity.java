package com.example.mainactivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mn;
    private TextView answer;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mn =(EditText)findViewById(R.id.matrikelnummer);
        answer =(TextView)findViewById(R.id.textViewAnswer);

    }

    //onClick to send Matrikelnummer to the server
    public void onClickAbschicken(View v) {
        //LogMessage nur für Testzwecke
        Log.v("LogMessage", "I am here");
        client = new Client(mn, answer);
        client.execute();
    }

    //method to do the calculation of the Quersumme and the binary Quersumme
    public void onClickCalc(View v) {

        //Matrikelnummer als String speichern
        String requestMsg = mn.getText().toString();
        char[] matrikelnummer = requestMsg.toCharArray();

        //calculateQuersumme berechnen = 1+2+3+4+5...
        int quersumme = 0;

        for (int i = 0; i < matrikelnummer.length; i++) {
            char charInString = matrikelnummer[i];
            quersumme = quersumme + Integer.parseInt(Character.toString(charInString));
        }

        //binary
        String binärQuersumme = Integer.toBinaryString(quersumme);
        answer.setText("Quersumme lautet: " + quersumme + "\n" + "Quersumme (binär) lautet: " + binärQuersumme);

    }


}
