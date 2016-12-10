package com.developers.sparshasahasridharswain.android_arduino_app;

import android.Manifest;
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

public class MainActivity extends AppCompatActivity {
    EditText number;
    Button make_call;
    Context c;
    AudioManager audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    }

    public void music_controls(Music m) {
        if (m.next) {
            Intent i = new Intent("com.android.music.musicservicecommand");

            i.putExtra("command", "next");
            MainActivity.this.sendBroadcast(i);
        } else if (m.previous) {
            Intent i = new Intent("com.android.music.musicservicecommand");

            i.putExtra("command", "previous");
            MainActivity.this.sendBroadcast(i);
        } else if (m.play) {
            Intent i = new Intent("com.android.music.musicservicecommand");

            i.putExtra("command", "play");
            MainActivity.this.sendBroadcast(i);
        } else if (m.pause) {
            Intent i = new Intent("com.android.music.musicservicecommand");

            i.putExtra("command", "pause");
            MainActivity.this.sendBroadcast(i);
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
}
