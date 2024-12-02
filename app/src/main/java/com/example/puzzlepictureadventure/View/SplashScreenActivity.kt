package com.example.puzzlepictureadventure.View

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.puzzlepictureadventure.MainActivity
import com.example.puzzlepictureadventure.R

class   SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Tetap menggunakan layout XML untuk splash screen
        setContentView(R.layout.activity_splash_screen)

        // Simulasi loading atau delay (3 detik)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Menutup SplashScreenActivity
        }, 3000)
    }
}
