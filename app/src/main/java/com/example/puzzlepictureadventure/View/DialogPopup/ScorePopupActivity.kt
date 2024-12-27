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

class ScorePopupActivity : DialogFragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk dialog
        val view = inflater.inflate(R.layout.activity_score_popup, container, false)

        // Mengatur background dialog menjadi transparan
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)

        return view
    }

    // Mengatur tampilan dialog agar dapat mengabaikan frame dan margin
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Optional: Mengatur dialog untuk menampilkan seluruh layar atau sesuai kebutuhan
        dialog?.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}
