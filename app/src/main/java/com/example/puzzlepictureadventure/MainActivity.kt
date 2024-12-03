package com.example.puzzlepictureadventure

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageView
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import com.example.puzzlepictureadventure.View.LevelActivity

class MainActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null  // Deklarasi MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menginisialisasi MediaPlayer dengan file audio dari folder raw
        mediaPlayer = MediaPlayer.create(this, R.raw.backsound)  // Pastikan backsound.mp3 ada di res/raw
        mediaPlayer?.isLooping = true  // Mengatur agar musik diputar berulang
        mediaPlayer?.start()  // Memulai pemutaran musik

        // Memeriksa status musik dari SharedPreferences
        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        val isMusicOn = sharedPreferences.getBoolean("isMusicOn", true)

        if (!isMusicOn) {
            mediaPlayer?.pause()  // Musik dimatikan, pause pemutaran
        }

        // Tombol pengaturan diklik untuk menampilkan dialog
        val btnSetting = findViewById<ImageView>(R.id.btnsetting)
        btnSetting.setOnClickListener {
            val successNotificationDialog = SettingPopupActivity()
            successNotificationDialog.show(supportFragmentManager, "SuccessNotificationDialog")
        }

        val btnPlay = findViewById<ImageView>(R.id.btnplay)
        btnPlay.setOnClickListener {
            val intent = Intent(this, LevelActivity::class.java)  // Ganti LevelActivity dengan nama Activity tujuan
            startActivity(intent)  // Memulai LevelActivity
        }

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

    // Fungsi untuk mengubah status musik
    fun setMusicStatus(isMusicOn: Boolean) {
        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isMusicOn", isMusicOn)
        editor.apply()

        if (isMusicOn) {
            mediaPlayer?.start()  // Nyalakan musik
        } else {
            mediaPlayer?.pause()  // Matikan musik
        }
    }
}
