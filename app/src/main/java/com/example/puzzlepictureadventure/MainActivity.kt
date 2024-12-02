package com.example.puzzlepictureadventure

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null  // Deklarasi MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Menginisialisasi MediaPlayer dengan file audio dari folder raw
        mediaPlayer = MediaPlayer.create(this, R.raw.backsound)  // Pastikan backsound.mp3 ada di res/raw
        mediaPlayer?.isLooping = true  // Mengatur agar musik diputar berulang
        mediaPlayer?.start()  // Memulai pemutaran musik

        // Mengatur padding sesuai dengan system bars (untuk edge-to-edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Menghentikan dan melepaskan sumber daya MediaPlayer
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
