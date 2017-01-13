package com.developers.sparshasahasridharswain.bluetooth_chat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ip_address extends AppCompatActivity {
    EditText ip;
    Button enter;
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    String get="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_address);
        ip=(EditText)findViewById(R.id.ip);
        enter=(Button)findViewById(R.id.enter);
        shared= getSharedPreferences("myshared",Context.MODE_PRIVATE);
        editor=shared.edit();
        get=shared.getString("ipaddress","");
        ip.setText(get);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ip.getText().toString().equals(get) && get!="") {
                    editor.putString("ipaddress", ip.getText().toString());
                    editor.apply();
                    Intent intent=new Intent(Ip_address.this,chat_interface.class);
                    intent.putExtra("address",ip.getText().toString());
                }
                else if(ip.getText().toString().equals(get))
                {
                    editor.putString("ipaddress", ip.getText().toString());
                    editor.apply();
                    Intent intent=new Intent(Ip_address.this,chat_interface.class);
                    intent.putExtra("address",ip.getText().toString());
                }

            }
        });






    }
}
