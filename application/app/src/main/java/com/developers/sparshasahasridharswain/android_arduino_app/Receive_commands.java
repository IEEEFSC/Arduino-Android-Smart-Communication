package com.developers.sparshasahasridharswain.android_arduino_app;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by Sparsha Saha on 12/14/2016.
 */

public class Receive_commands extends AsyncTask<Void,Void,Void> {
    static byte b[]=new byte[1];
    int z=0;
    @Override
    protected void onPostExecute(Void aVoid) {
        String x=new String(b);
        MainActivity.continuous_recv(x);

        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            z=MainActivity.bsocket.getInputStream().read(b,0,b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
