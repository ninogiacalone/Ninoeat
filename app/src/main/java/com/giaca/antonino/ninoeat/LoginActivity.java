package com.giaca.antonino.ninoeat;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


/**
 * Created by anton on 31/01/2019.
 */

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener{
    TextView emailTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        emailTV=findViewById(R.id.email_tv);
        Intent intent = getIntent();

        final String email=intent.getStringExtra("email");
        emailTV.setText(email);

        final TextView sendemail=findViewById(R.id.send_email);

        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Oggetto Email");
                startActivity(Intent.createChooser(intent,""));
            }
        });
    }


    @Override
    public void onClick(View view) {

    }
}



