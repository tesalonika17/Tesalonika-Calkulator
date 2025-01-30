package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Pastikan ini sesuai dengan nama layout XML kamu

        // Inisialisasi EditText dan Button berdasarkan ID di layout XML
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Fungsi untuk menangani klik tombol login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan nilai dari input username dan password
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Validasi login
                if (username.equals("tesa") && password.equals("tesa123")) {
                    // Jika login berhasil, pindah ke Laman_Utama.java
                    Intent intent = new Intent(LoginActivity.this, Laman_Utama.class);
                    startActivity(intent);
                    finish();  // Menutup LoginActivity
                } else {
                    // Jika login gagal, tampilkan pesan
                    Toast.makeText(LoginActivity.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
