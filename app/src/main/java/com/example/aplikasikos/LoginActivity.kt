package com.example.aplikasikos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvDaftar = findViewById<TextView>(R.id.tvDaftar)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Username dan password wajib diisi", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: ganti dengan validasi login yang sesungguhnya (misalnya cek ke database/API)
                Toast.makeText(this, "Login berhasil, selamat datang $username", Toast.LENGTH_SHORT).show()

                // Pindah ke halaman utama/beranda setelah login berhasil
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        tvDaftar.setOnClickListener {
            Toast.makeText(this, "Fitur pendaftaran belum tersedia", Toast.LENGTH_SHORT).show()
        }
    }
}
