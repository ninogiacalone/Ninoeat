/**
 * Created by anton on 31/01/2019.
 */    import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.giaca.antonino.ninoeat.R;


public class RegisterActivity extends AppCompatActivity{





        boolean emailok=false;
        boolean passwordok=false;
        boolean numerook=false;
        EditText emailEt;
        EditText passwordEt;
        EditText numerotel;
        Button register;

        @Override

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.register_activity);
            emailEt = findViewById(R.id.email_et);
            passwordEt = findViewById(R.id.password_et);
            register = findViewById(R.id.register_Butto2);
            numerotel = findViewById(R.id.phone_et);

            emailEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

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


        private void btncheck(Button btn){
            if(emailok&&passwordok&&numerook){
                btn.setEnabled(true);
            }else
                btn.setEnabled(false);
        }
        private void telcheck(String numero){
            if(numero.length()>6)
                numerook=true;
            else
                numerook=false;

        }
    }



