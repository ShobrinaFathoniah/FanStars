package com.shobrinaf.stars.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.shobrinaf.stars.core.ui.FavoriteStarAdapter
import com.shobrinaf.stars.detail.DetailStarActivity
import com.shobrinaf.stars.di.ModuleDependencies
import com.shobrinaf.stars.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    ModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorite Stars Picture"

        val starAdapter = FavoriteStarAdapter()

        starAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailStarActivity::class.java)
            intent.putExtra(DetailStarActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        getFavoriteData(starAdapter)
    }

    private fun getFavoriteData(starAdapter: FavoriteStarAdapter) {
        favoriteViewModel.favorite.observe(this) { stars ->
            if (stars != null) {
                if (stars.isEmpty()) {
                    binding.ivEmpty.visibility = View.VISIBLE
                    binding.rvTourism.visibility = View.GONE
                } else {
                    binding.ivEmpty.visibility = View.GONE
                    starAdapter.setData(stars)
                    binding.rvTourism.visibility = View.VISIBLE
                }

                with(binding.rvTourism) {
                    layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    setHasFixedSize(true)
                    adapter = starAdapter
                }
            }
        }
    }
}