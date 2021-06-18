package com.shobrinaf.stars.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.shobrinaf.stars.core.domain.model.Stars
import com.shobrinaf.stars.databinding.ActivityDetailSearchStarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailSearchStarActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailSearchStarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSearchStarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailStar = intent.getParcelableExtra<Stars>(EXTRA_DATA)
        showDetailStar(detailStar)
    }

    private fun showDetailStar(detailStar: Stars?) {
        detailStar?.let {
            supportActionBar?.title = detailStar.title
            with(binding.content) {
                tvDetailDescription.text = detailStar.description
                tvDetailTitle.text = detailStar.title
                tvDetailDate.text = detailStar.date
                tvDetailCopyright.text = StringBuilder("Credit: ${detailStar.copyright}")

                buttonWebsite.setOnClickListener {
                    val url = detailStar.apodSite
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(browserIntent)
                }
            }
            Glide.with(this)
                .load(detailStar.url)
                .into(binding.ivDetailImage)
        }
    }
}
