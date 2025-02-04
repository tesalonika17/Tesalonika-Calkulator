package com.example.calculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnDeleteAll;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
//        btnDeleteAll = findViewById(R.id.btnDeleteAll);
        dbHelper = new DatabaseHelper(this);

        // Cek apakah ada user terdaftar di database
        if (!dbHelper.hasRegisteredUser()) {
            Toast.makeText(this, "Belum ada pengguna terdaftar, silakan daftar terlebih dahulu.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, SignUp.class));
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Harap isi semua bidang!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (dbHelper.checkUser(email, password)) {
                    Toast.makeText(LoginActivity.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, Laman_Utama.class));
                    finish();
                } else {
                    showLoginErrorDialog();
                }
            }
        });

//        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dbHelper.deleteAllUsers();
//                Toast.makeText(LoginActivity.this, "Semua data pengguna telah dihapus!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void showLoginErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login Gagal");
        builder.setMessage("Email atau Password salah. Silakan coba lagi.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
