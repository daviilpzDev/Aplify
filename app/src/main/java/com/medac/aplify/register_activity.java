package com.medac.aplify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;

public class register_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox2);
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
        }

    }
}