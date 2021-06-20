package com.shobrinaf.stars.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.shobrinaf.stars.R
import com.shobrinaf.stars.core.domain.model.Stars
import com.shobrinaf.stars.databinding.ActivityDetailStarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailStarActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailStarBinding

    private val detailStarViewModel: DetailStarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStarBinding.inflate(layoutInflater)
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

            var statusFavorite = detailStar.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailStarViewModel.setFavoriteStar(detailStar, statusFavorite)
                setStatusFavorite(statusFavorite)
            }

            binding.ivDetailImage.setOnClickListener {
                val intent = Intent(this, FullImageActivity::class.java)
                intent.putExtra(FullImageActivity.EXTRA_DATA, detailStar)
                startActivity(intent)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }
}
