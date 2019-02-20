package com.giaca.antonino.ninoeat.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.services.RestController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anton on 28/01/2019.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,Response.Listener<String>,Response.ErrorListener {
    private static final String TAG =RegisterActivity.class.getName();
    Button loginButton;
    Button RegisterBtn;
    EditText emailEt;
    EditText passwordEt;
    Switch switchbtn;
    LinearLayout ll;
    boolean a = false;
    final int len_pass = 6;
    RestController restController;
    public final String EMAIL_KEY="email";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.login_activity);
        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);
        loginButton = findViewById(R.id.login_Button);
        RegisterBtn = findViewById(R.id.register_Button);
        switchbtn = findViewById(R.id.switch_btn);

        loginButton.setOnClickListener(this);
        RegisterBtn.setOnClickListener(this);
        switchbtn.setOnClickListener(this);
        ll = findViewById(R.id.ll_layout);
restController= new RestController(this);
        loginButton = findViewById(R.id.login_Button);
        Log.i("MainActivity", "Activity created");
        if (hasInvitationCode())
            RegisterBtn.setVisibility(View.INVISIBLE);
    }

    private boolean hasInvitationCode() {

        return false;
    }


    private void doLogin() {
      /*  if(checkemail()&&checkpasw()==true){
            Intent intent1= new Intent(LoginActivity.this,RegisterActivity.class);
            String email=emailEt.getText().toString();
            intent1.putExtra("email",email);
            startActivity(intent1);
            finish();
        }*/
        String email1 = emailEt.getText().toString();
        String password1 = passwordEt.getText().toString();
        if (!checkemail(email1)) {
            showToast(" email invalida");
            return;

        }
        if (!checkpasw(password1)) {
            showToast("pass invalida");
            return;

        }
        Map<String,String> params= new HashMap<>();
        params.put("identifier",email1);
        params.put("password",password1);


        restController.postRequest(User.LOGIN_ENDPOINT,params,this,this);
        showToast("eseguito");
    }


    public void doRegister() {

        Intent intet = new Intent(LoginActivity.this, RegisterActivity.class);
        setContentView(R.layout.register_activity);
        startActivity(intet);
        finish();

    }

    private boolean checkpasw(String password1) {
        if (passwordEt.getText().length() > len_pass) {

            return true;


        } else {

            return false;
        }
    }

    private boolean checkemail(String email) {
        email = emailEt.getText().toString();

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

    private void showToast(String stringa) {
        Toast.makeText(this, stringa, Toast.LENGTH_LONG).show();


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.login_Button) {
            doLogin();

        } else if (view.getId() == R.id.register_Button) {
            startActivity(new Intent(this, RegisterActivity.class));
        }
        if (view.getId() == R.id.switch_btn) {
            changecolour(ll);
        }
    }

    private void changecolour(LinearLayout ll) {
        if (!a) {
            ll.setBackgroundColor(Color.GRAY);
            a = true;
        } else {
            ll.setBackgroundColor(Color.WHITE);
            a = false;
        }

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG,error.getMessage());
        showToast(error.getMessage());

    }

    @Override
    public void onResponse(String response) {
Log.d(TAG,response);


Intent i =new Intent();
i.putExtra("response",response);
setResult(Activity.RESULT_OK,i);
finish();

        try {
            JSONObject jsonResponse= new JSONObject(response );
            SharedPreferencesUtils.putValue(this,"",jsonResponse.getString("jwt"));
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MainActivity.LOGIN_ACTION));


            finish();
        } catch (JSONException e) {
            Log.e(TAG,e.getMessage());
        }


    }
}

