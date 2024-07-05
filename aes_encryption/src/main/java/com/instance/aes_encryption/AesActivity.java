package com.instance.aes_encryption;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class AesActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private static final String AES_KEY = "12345678901234567890123456789012"; // This should be 32 characters long for AES-256

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aes);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        MaterialButton submitButton = findViewById(R.id.button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encryptAndSubmitData();
            }
        });

        MaterialButton backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AesActivity.this, "Exited from the module", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void encryptAndSubmitData() {
        try {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            String encryptedUsername = AESEncryption.encrypt(username, AES_KEY);
            String encryptedPassword = AESEncryption.encrypt(password, AES_KEY);

            Intent intent = new Intent(this, DisplayEncryptedActivity.class);
            intent.putExtra("encryptedUsername", encryptedUsername);
            intent.putExtra("encryptedPassword", encryptedPassword);
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println(e.getMessage());
        }
    }
}
