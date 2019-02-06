package com.giaca.antonino.ninoeat.ui.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.giaca.antonino.ninoeat.R;

/**
 * Created by anton on 28/01/2019.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button loginButton;
    Button RegisterBtn;
    EditText  emailEt;
    EditText passwordEt;
    Switch switchbtn;
    LinearLayout ll;
    boolean a=false;
    final  int len_pass=6;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        setContentView(R.layout.login_activity);
        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);
        loginButton = findViewById(R.id.login_Button);
        RegisterBtn = findViewById(R.id.register_Button);
        switchbtn=findViewById(R.id.switch_btn);
        if(hasInvitationCode())
            RegisterBtn.setVisibility(View.INVISIBLE);
        loginButton.setOnClickListener(this);
        RegisterBtn.setOnClickListener(this);
        switchbtn.setOnClickListener(this);
        ll=findViewById(R.id.ll_layout);

        loginButton=findViewById(R.id.login_Button);
        Log.i("MainActivity","Activity created");
    }
    private boolean hasInvitationCode(){

        return false;
    }







    private void doLogin() {
        if(checkemail()&&checkpasw()==true){
            Intent intent1= new Intent(LoginActivity.this,RegisterActivity.class);
            String email=emailEt.getText().toString();
            intent1.putExtra("email",email);
            startActivity(intent1);
            finish();
        }





    }





    public void doRegister(){

        Intent intet= new Intent(LoginActivity.this,RegisterActivity.class);
        setContentView(R.layout.register_activity);
        startActivity(intet);
        finish();

    }
    private boolean checkpasw(){
        if (passwordEt.getText().length()>len_pass) {
            String stringa = "password ok";
            showToast(stringa);
            return true;


        } else {
            String stringa = "password errata";
            showToast(stringa);
            return false;
        }
    }
    private boolean checkemail(){
        String email = emailEt.getText().toString();

        if (email.contains("@") && email.contains(".") && email.length() > 2) {
            String stringa1 = "email ok";
            showToast(stringa1);
            return true;


        } else {
            String string1 = "email errata";
            showToast(string1);
            return false;


        }
    }

    private void showToast(String stringa){
        Toast.makeText(this,stringa ,Toast.LENGTH_LONG).show();


    }
    @Override
    public void onClick(View view) {
        //TODO do something
        if(view.getId()==R.id.login_Button){
            doLogin();
            //TODO do login
        }else if (view.getId()==R.id.register_Button){
            //TODO go to register activity
            doRegister();
        }
        if(view.getId()==R.id.switch_btn){
            changecolour(ll);
        }
    }

    private void changecolour(LinearLayout ll) {
        if (!a) {
            ll.setBackgroundColor(Color.GRAY);
            a = true;
        } else {
            ll.setBackgroundColor(Color.WHITE);
            a=false;
        }

    }
}
