package com.shobrinaf.stars.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.shobrinaf.stars.core.domain.model.Stars
import com.shobrinaf.stars.databinding.ActivityFullImageBinding

class FullImageActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityFullImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val stars = intent.getParcelableExtra<Stars>(EXTRA_DATA)

        showFullImage(stars)
    }

    private fun showFullImage(stars: Stars?) {
        Toast.makeText(
            this, "Rotate your phone to landscape to make full image",
            Toast.LENGTH_SHORT
        ).show()

        stars?.let {
            with(binding) {
                Glide.with(this@FullImageActivity)
                    .load(stars.hdurl)
                    .into(fullImage)
            }
        }
    }
}