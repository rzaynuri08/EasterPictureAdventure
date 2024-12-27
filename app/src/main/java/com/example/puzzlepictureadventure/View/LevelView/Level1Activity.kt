package com.example.puzzlepictureadventure.View.LevelView

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.puzzlepictureadventure.R
import com.example.puzzlepictureadventure.ScorePopupActivity
import com.example.puzzlepictureadventure.SettingPopupActivity

class Level1Activity : AppCompatActivity() {
    private lateinit var txtTimer: TextView

    // Set untuk melacak kata yang telah dimasukkan
    private val wordsCompleted = mutableSetOf<String>()
    private val requiredWords = setOf("katak", "air", "teratai", "ikan")
    private var timeRemainingInMillis: Long = 0 // Variabel untuk menyimpan waktu tersisa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_level1)

        // Mengatur padding untuk inset
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtTimer = findViewById(R.id.txtTimer)

        // Mulai hitung mundur 10 menit
        startCountDownTimer(10 * 60 * 500) // 10 menit dalam milidetik

        // Temukan btnKlikOK dan EditText
        val btnKlikOk = findViewById<ImageButton>(R.id.btnKlikOK)
        val editKetik = findViewById<EditText>(R.id.editKetik)
        val A1 = findViewById<TextView>(R.id.A1)
        val A2B1 = findViewById<TextView>(R.id.A2B1)
        val A3 = findViewById<TextView>(R.id.A3)
        val A4 = findViewById<TextView>(R.id.A4)
        val A5 = findViewById<TextView>(R.id.A5)

        val B2 = findViewById<TextView>(R.id.B2)
        val B3C3 = findViewById<TextView>(R.id.B3C3)

        val C1 = findViewById<TextView>(R.id.C1)
        val C2 = findViewById<TextView>(R.id.C2)
        val C4 = findViewById<TextView>(R.id.C4)
        val C5 = findViewById<TextView>(R.id.C5)
        val C6 = findViewById<TextView>(R.id.C6)
        val C7D1 = findViewById<TextView>(R.id.C7D1)

        val D2 = findViewById<TextView>(R.id.D2)
        val D3 = findViewById<TextView>(R.id.D3)
        val D4 = findViewById<TextView>(R.id.D4)

        // Tambahkan setOnClickListener pada btnKlikOK
        btnKlikOk.setOnClickListener {
            val inputText = editKetik.text.toString().trim() // Ambil teks dari EditText

            // Logika if berdasarkan input
            when (inputText) {
                "katak" -> {
                    A1.visibility = View.VISIBLE
                    A2B1.visibility = View.VISIBLE
                    A3.visibility = View.VISIBLE
                    A4.visibility = View.VISIBLE
                    A5.visibility = View.VISIBLE
                    wordsCompleted.add(inputText) // Tambahkan ke kata yang telah diselesaikan
                    editKetik.setText("")
                }
                "air" -> {
                    A2B1.visibility = View.VISIBLE
                    B2.visibility = View.VISIBLE
                    B3C3.visibility = View.VISIBLE
                    wordsCompleted.add(inputText)
                    editKetik.setText("")
                }
                "teratai" -> {
                    C1.visibility = View.VISIBLE
                    C2.visibility = View.VISIBLE
                    B3C3.visibility = View.VISIBLE
                    C4.visibility = View.VISIBLE
                    C5.visibility = View.VISIBLE
                    C6.visibility = View.VISIBLE
                    C7D1.visibility = View.VISIBLE
                    wordsCompleted.add(inputText)
                    editKetik.setText("")
                }
                "ikan" -> {
                    C7D1.visibility = View.VISIBLE
                    D2.visibility = View.VISIBLE
                    D3.visibility = View.VISIBLE
                    D4.visibility = View.VISIBLE
                    wordsCompleted.add(inputText)
                    editKetik.setText("")
                }
                else -> {
                    // Jika input tidak sesuai
                    Toast.makeText(this, "Kata yang kamu masukkan salah!", Toast.LENGTH_SHORT).show()
                }
            }

            // Cek apakah semua kata telah diselesaikan
            if (wordsCompleted.containsAll(requiredWords)) {
                val successNotificationDialog = ScorePopupActivity()
                successNotificationDialog.show(supportFragmentManager, "SuccessNotificationDialog")
            }
        }
    }

    private fun startCountDownTimer(durationInMillis: Long) {
        object : CountDownTimer(durationInMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                // Simpan waktu yang tersisa
                timeRemainingInMillis = millisUntilFinished

                // Konversi waktu yang tersisa ke menit dan detik
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60

                // Format waktu ke "mm:ss"
                txtTimer.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                // Aksi ketika timer selesai
                txtTimer.text = "00:00"
                Toast.makeText(this@Level1Activity, "Waktu habis!", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun calculateStars() {
        val totalTimeInMillis = 10 * 60 * 1000 // Total waktu dalam milidetik
        val timeUsedInMillis = totalTimeInMillis - timeRemainingInMillis

        val timeUsedInMinutes = timeUsedInMillis / 1000.0 / 60.0

        val stars = when {
            timeUsedInMinutes >= 3.50 -> 3
            timeUsedInMinutes >= 2.0 -> 2
            else -> 1
        }
    }
}
