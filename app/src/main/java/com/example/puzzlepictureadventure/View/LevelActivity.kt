package com.example.puzzlepictureadventure.View

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puzzlepictureadventure.R
import com.example.puzzlepictureadventure.adapter.LevelAdapter
import com.example.puzzlepictureadventure.database.DatabaseHelper
import com.example.puzzlepictureadventure.model.LevelData

class LevelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        // Back button setup
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        // Setup RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewLevels)
        recyclerView.layoutManager = GridLayoutManager(this, 3) // Grid layout with 3 columns

        // Fetch data from database
        val levelList = fetchLevelsFromDatabase()

        // Initialize adapter with database data
        val levelAdapter = LevelAdapter(this, levelList) { levelData ->
            if (levelData.akses == 1) {
                // Handle unlocked level click
                // TODO: Navigate to the selected level
            } else {
                // Handle locked level click
                // TODO: Show a message that the level is locked
            }
        }

        recyclerView.adapter = levelAdapter
    }

    private fun fetchLevelsFromDatabase(): List<LevelData> {
        val dbHelper = DatabaseHelper(this)
        val cursor = dbHelper.getAllLevelData() // Assuming this function is in DatabaseHelper
        val levelList = mutableListOf<LevelData>()

        if (cursor.moveToFirst()) {
            do {
                val level = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LEVEL))
                val bintang = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_BINTANG))
                val akses = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_AKSES))
                levelList.add(LevelData(level, bintang, akses))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return levelList
    }
}
