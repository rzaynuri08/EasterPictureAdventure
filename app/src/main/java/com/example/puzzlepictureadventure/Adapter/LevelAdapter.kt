package com.example.puzzlepictureadventure.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.puzzlepictureadventure.R
import com.example.puzzlepictureadventure.model.LevelData

class LevelAdapter(
    private val context: Context,
    private val levelList: List<LevelData>,
    private val onLevelClick: (LevelData) -> Unit
) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {

    // ViewHolder class
    inner class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnKlikLevel: ImageView = itemView.findViewById(R.id.btnKliklevel)
        val txtLevel: TextView = itemView.findViewById(R.id.txtLevel)
        val imgBintang: ImageView = itemView.findViewById(R.id.imgBintang)

        fun bind(levelData: LevelData) {
            // Set text for level number
            txtLevel.text = levelData.level.toString()

            // Set image for level access
            btnKlikLevel.setImageResource(
                if (levelData.akses == 1) R.drawable.unlock else R.drawable.lock
            )

            // Set star image based on bintang value
            val bintangDrawable = when (levelData.bintang) {
                1 -> R.drawable.b1
                2 -> R.drawable.b2
                3 -> R.drawable.b3
                else -> R.drawable.b0
            }
            imgBintang.setImageResource(bintangDrawable)

            // Handle click event
            itemView.setOnClickListener { onLevelClick(levelData) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_level, parent, false)
        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        holder.bind(levelList[position])
    }

    override fun getItemCount(): Int = levelList.size
}
