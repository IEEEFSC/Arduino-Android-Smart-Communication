package com.developers.sparshasahasridharswain.android_arduino_app;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by Sparsha Saha on 12/13/2016.
 */

public class AsynchronousTask extends AsyncTask<Void,Void,Void> {
Context context;
    AsynchronousTask(Context c)
    {
        context=c;
    }
    @Override
    protected Void doInBackground(Void... params) {
        try {
            if (MainActivity.bsocket == null || !MainActivity.isbtconnected == false) {
                MainActivity.badapter=(BluetoothAdapter.getDefaultAdapter());
                BluetoothDevice bdevice=MainActivity.badapter.getRemoteDevice(MainActivity.address);
                MainActivity.bsocket = bdevice.createInsecureRfcommSocketToServiceRecord(MainActivity.myUUID);
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                MainActivity.bsocket.connect();
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid);
    }
}
