package com.developers.sparshasahasridharswain.bluetooth_chat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.UUID;

public class chat_interface extends AppCompatActivity {
Intent prev_intent;
    static Context con;
    static BluetoothAdapter bluetoothAdapter=null;
    static BluetoothSocket bluetoothSocket=null;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    Button test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_interface);
        test=(Button)findViewById(R.id.button);
        prev_intent=getIntent();

        try {
            if (bluetoothSocket == null) {
                bluetoothAdapter=(BluetoothAdapter.getDefaultAdapter());
                BluetoothDevice bdevice=bluetoothAdapter.getRemoteDevice(prev_intent.getStringExtra("address"));
                bluetoothSocket = bdevice.createInsecureRfcommSocketToServiceRecord(myUUID);
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                bluetoothSocket.connect();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bluetoothSocket.getOutputStream().write("cocky".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });







    }
}
