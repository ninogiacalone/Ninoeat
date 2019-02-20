package com.giaca.antonino.ninoeat.ui.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.giaca.antonino.ninoeat.R;

/**
 * Created by anton on 18/02/2019.
 */

public class ProfileActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {


        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_profile);
    }
}
