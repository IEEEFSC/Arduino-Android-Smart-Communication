package com.developers.sparshasahasridharswain.android_arduino_app;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText number;
    Button make_call;
    static Context c;
    AudioManager audio;
    static String res;
    static Context con;
    static BluetoothAdapter badapter=null;
    static BluetoothSocket bsocket=null;
    static boolean isbtconnected=false;
    static String address="98:D3:32:10:8A:93";
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con=this;
        number = (EditText) findViewById(R.id.phone_number);
        make_call = (Button) findViewById(R.id.call);
        c = this;
        audio = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        make_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+91" + number.getText().toString()));
                if (ActivityCompat.checkSelfPermission(c, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                //startActivity(i);
            }
        });
        AsynchronousTask at=new AsynchronousTask(this);
        try {
            at.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        /*try {
            bsocket.getOutputStream().write("copy that".toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Receive_commands rc=new Receive_commands();
        rc.execute();

    }

    public static void music_controls(Music m) {
        if (m.next) {
            Intent i = new Intent("com.android.music.musicservicecommand");

            i.putExtra("command", "next");
            c.sendBroadcast(i);
        } else if (m.previous) {
            Intent i = new Intent("com.android.music.musicservicecommand");

            i.putExtra("command", "previous");
            c.sendBroadcast(i);
        } else if (m.play) {
            Intent i = new Intent("com.android.music.musicservicecommand");

            i.putExtra("command", "play");
            c.sendBroadcast(i);
        } else if (m.pause) {
            Intent i = new Intent("com.android.music.musicservicecommand");

            i.putExtra("command", "pause");
            c.sendBroadcast(i);
        }
    }


    public void Outgoingcall(Call_Outgoing g) {
        String f = g.phone_number;
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+91" + f));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(i);
    }

    public static void continuous_recv(String f)
    {
        if(f.equals("#"))
        {
            res="";
            Receive_commands r=new Receive_commands();
            r.execute();

        }
        else if(f.equals("~"))
        {
            if(res.charAt(0)=='1')
            {
                Music m=new Music();
                m=Music.convert_from_string(res);
                music_controls(m);
                Receive_commands r=new Receive_commands();
                r.execute();
            }
            else if(res.charAt(0)=='9')
            {
                //Terminate Async task to avoid weird errors
            }


        }
        else
        {
            res=res+f;
            Receive_commands r=new Receive_commands();
            r.execute();
        }


    }
}
