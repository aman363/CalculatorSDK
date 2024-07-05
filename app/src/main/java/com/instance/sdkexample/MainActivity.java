package com.instance.sdkexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.instance.librarymodule.calculator;

public class MainActivity extends AppCompatActivity {

    Button button,buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        buttonLogin = findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnclick(v);
            }
        });

    }
    public void btnclick(View v) {

                Intent i = new Intent(this, calculator.class);
                startActivity(i);
    }public void LoginButtonClick(View v) {

                Intent i = new Intent(this, com.instance.aes_encryption.AesActivity.class);
                startActivity(i);
    }


}
