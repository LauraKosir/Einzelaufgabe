package com.example.mainactivity;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends AsyncTask<Void, Void, Void> {

    private Socket socket;
    private OutputStream os;
    private InputStream is;

    private EditText mn;
    private TextView answer;
    private TextView calculation;

    private String message;

    public Client(EditText mn, TextView answer, TextView calculation) {
        this.mn = mn;
        this.answer = answer;
        this.calculation = calculation;
    }

    private void run()
    {
        try {
            String host = "se2-isys.aau.at";
            int port = 53212;

            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);

            //Send the message to the server
            os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            //String number = "2";
            String requestMsg = mn.getText().toString();

            bw.write(requestMsg);
            bw.newLine();
            bw.flush();
            Log.v("LogMessage","Message sent to the server : "+requestMsg);

            //Get the return message from the server
            String responseMsg = br.readLine();
            Log.v("LogMessage","Message received from the server : " +responseMsg);
            message = responseMsg;

            br.close();
            bw.close();
            socket.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        run();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        answer.setText(message);
    }
}