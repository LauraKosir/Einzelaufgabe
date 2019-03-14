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
    private TextView calculation;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mn =(EditText)findViewById(R.id.matrikelnummer);
        answer =(TextView)findViewById(R.id.textViewAnswer);
        calculation = (TextView)findViewById(R.id.textViewCalculation);


    }

    public void onClickAbschicken(View v) {
        Log.v("LogMessage", "I am here");
        client = new Client(mn, answer, calculation);
        client.execute();
    }


}
