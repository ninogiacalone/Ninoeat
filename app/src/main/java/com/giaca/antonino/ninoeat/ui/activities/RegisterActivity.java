package com.giaca.antonino.ninoeat.ui.activities;
/**
 * Created by anton on 31/01/2019.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.giaca.antonino.ninoeat.R;
import com.giaca.antonino.ninoeat.services.RestController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,Response.Listener<String>,Response.ErrorListener {


    private static final String TAG =RegisterActivity.class.getName();
    boolean emailok=false;
        boolean passwordok=false;
        boolean numerook=false;
        EditText emailEt,usernameEt;
        EditText passwordEt;
        EditText numerotel;
        Button register;
    RestController restController;

        @Override

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.register_activity);
            usernameEt=findViewById(R.id.username);
            emailEt = findViewById(R.id.email_et);
            passwordEt = findViewById(R.id.password_et);
            register = findViewById(R.id.register_Butto2);

            numerotel = findViewById(R.id.phone_et);


            restController =  new RestController(this);
            register.setOnClickListener(this);
            emailEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Log.i("before",charSequence.toString());
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String email = emailEt.getText().toString();
                    checkemail(email);
                    btncheck(register);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            passwordEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String psw = passwordEt.getText().toString();
                    checkpassword(psw);
                    btncheck(register);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            numerotel.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String numero = numerotel.getText().toString();
                    telcheck(numero);
                    btncheck(register);
                }


                @Override
                public void afterTextChanged(Editable editable) {


                }
            });
        }
        private void checkemail(String email){
            if (email.contains("@") && email.contains(".") && email.length() > 2 ) {
                emailok=true;

            } else {
                emailok=false;
            }
        }
        private void checkpassword(String password){
            if (password.length() > 6) {
                passwordok=true;

            } else {
                passwordok=false;
            }
        }


        private void btncheck(Button button){
            if(emailok && passwordok && numerook){
                button.setEnabled(true);
            }else
                button.setEnabled(false);
        }
        private void telcheck(String numero){
            if(numero.length()>6)
                numerook=true;
            else
                numerook=false;

        }


    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG,error.getMessage());
        Toast.makeText(this, error.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {
        Log.d(TAG,response);
        try {
            JSONObject jsonObject= new JSONObject(response);
            String accessToken=jsonObject.getString("jwt");
            User user=new User(jsonObject.getJSONObject("user"),accessToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.register_Butto2){
            Map<String,String> params = new HashMap<>();
            params.put("username",usernameEt.getText().toString());
            params.put("email",emailEt.getText().toString());
            params.put("password",passwordEt.getText().toString());

            restController.postRequest(User.REGISTER_ENDPOINT,params,this,this);
        }
    }
}



