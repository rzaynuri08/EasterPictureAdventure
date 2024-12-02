package com.example.puzzlepictureadventure

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.content.SharedPreferences
import android.content.Context

class SettingPopupActivity : DialogFragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk dialog
        val view = inflater.inflate(R.layout.activity_setting_popup, container, false)

        // Mengatur background dialog menjadi transparan
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // Mengambil referensi ke SharedPreferences
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Mengambil status musik dari SharedPreferences
        val isMusicOn = sharedPreferences.getBoolean("isMusicOn", true)  // Pastikan ini adalah SharedPreferences, bukan editor

        val btnMusik = view.findViewById<ImageView>(R.id.btnMusik)
        val txtMusik = view.findViewById<TextView>(R.id.txtMusik)

        // Mengatur tampilan awal berdasarkan status musik
        if (isMusicOn) {
            btnMusik.setImageResource(R.drawable.sound)
            txtMusik.setText("Musik : ON")
        } else {
            btnMusik.setImageResource(R.drawable.sound_off)
            txtMusik.setText("Musik : Off")
        }

        btnMusik.setOnClickListener {
            // Mengubah status musik
            if (isMusicOn) {
                // Matikan musik
                btnMusik.setImageResource(R.drawable.sound_off)
                txtMusik.setText("Musik : Off")
                editor.putBoolean("isMusicOn", false)  // Menyimpan status musik "Off"
            } else {
                // Nyalakan musik
                btnMusik.setImageResource(R.drawable.sound)
                txtMusik.setText("Musik : ON")
                editor.putBoolean("isMusicOn", true)  // Menyimpan status musik "ON"
            }

            // Simpan perubahan
            editor.apply()

            // Panggil fungsi di MainActivity untuk mengubah status musik
            // Pastikan SharedPreferences sudah diupdate terlebih dahulu
            val newMusicStatus = sharedPreferences.getBoolean("isMusicOn", true)  // Ambil status yang sudah diubah
            (requireActivity() as MainActivity).setMusicStatus(newMusicStatus)  // Kirim status musik ke MainActivity
        }

        view.findViewById<ImageButton>(R.id.btnback).setOnClickListener {
            dismiss()
        }

        return view
    }

    // Mengatur tampilan dialog agar dapat mengabaikan frame dan margin
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Optional: Mengatur dialog untuk menampilkan seluruh layar atau sesuai kebutuhan
        dialog?.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}
