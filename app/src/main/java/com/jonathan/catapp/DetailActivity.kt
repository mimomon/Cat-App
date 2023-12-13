package com.jonathan.catapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_CAT = "key_cat"
    }

    private lateinit var btnShare: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        btnShare = findViewById(R.id.action_share)

        val dataCat = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_CAT, Cat::class.java)
        } else {
            @Suppress ("DEPRECATION")
            intent.getParcelableExtra(KEY_CAT)
        }

        val detailName: TextView = findViewById(R.id.detail_name)
        val detailDescription: TextView = findViewById(R.id.detail_description)
        val detailImage: ImageView = findViewById(R.id.detail_image)

        if(dataCat != null) {
            detailName.text = dataCat.name
            detailDescription.text = dataCat.description
            detailImage.setImageResource(dataCat.photo)
        }

        val textToShare = dataCat?.description
        btnShare.setOnClickListener(View.OnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare)
            shareIntent.type = "text/plain"
            startActivity(Intent.createChooser(shareIntent, "Bagikan dengan: "))
        })
    }




}