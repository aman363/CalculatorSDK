package com.instance.aes_encryption;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayEncryptedActivity extends AppCompatActivity {

    private TextView encryptedUsernameTextView;
    private TextView encryptedPasswordTextView;
    private TextView decryptedUsernameTextView;
    private TextView decryptedPasswordTextView;
    private String encryptedUsername;
    private String encryptedPassword;
    private static final String AES_KEY = "12345678901234567890123456789012"; // Ensure this matches the key used for encryption

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_encrypted);

        encryptedUsernameTextView = findViewById(R.id.encrypted_username);
        encryptedPasswordTextView = findViewById(R.id.encrypted_password);
        decryptedUsernameTextView = findViewById(R.id.decrypted_username);
        decryptedPasswordTextView = findViewById(R.id.decrypted_password);

        encryptedUsername = getIntent().getStringExtra("encryptedUsername");
        encryptedPassword = getIntent().getStringExtra("encryptedPassword");

        encryptedUsernameTextView.setText("Encrypted Username:\n"+encryptedUsername);
        encryptedPasswordTextView.setText("Encrpyted Password:\n"+encryptedPassword);

        findViewById(R.id.decrypt_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decryptAndShowData();
            }
        });
    }

    private void decryptAndShowData() {
        try {
            String decryptedUsername = AESEncryption.decrypt(encryptedUsername, AES_KEY);
            String decryptedPassword = AESEncryption.decrypt(encryptedPassword, AES_KEY);

            decryptedUsernameTextView.setText("Decrypted UserName:\n"+decryptedUsername);
            decryptedPasswordTextView.setText("Decrypted Password:\n"+decryptedPassword);

            decryptedUsernameTextView.setVisibility(View.VISIBLE);
            decryptedPasswordTextView.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Decryption Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
