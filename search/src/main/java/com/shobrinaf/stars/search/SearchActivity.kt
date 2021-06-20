package com.shobrinaf.stars.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.shobrinaf.stars.core.data.Resource
import com.shobrinaf.stars.core.ui.SearchStarAdapter
import com.shobrinaf.stars.detail.DetailSearchStarActivity
import com.shobrinaf.stars.di.ModuleDependencies
import com.shobrinaf.stars.search.databinding.ActivitySearchBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val searchViewModel: SearchViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivitySearchBinding
    private var query: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerSearchComponent.builder()
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
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorite Stars Picture"

        val starAdapter = SearchStarAdapter()
        val textInput = binding.inputQuery.editText

        starAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailSearchStarActivity::class.java)
            intent.putExtra(DetailSearchStarActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        if (query == "") {
            binding.viewEmpty.visibility = View.VISIBLE
            binding.rvStar.visibility = View.GONE
        }

        textInput?.doOnTextChanged { text, start, before, count ->
            query = text.toString()

            starAdapter.notifyDataSetChanged()
            getData(starAdapter, query!!)
            binding.viewEmpty.visibility = View.GONE
        }

        binding.btnSearch.setOnClickListener {
            query = textInput?.text.toString()
            starAdapter.notifyDataSetChanged()
            getData(starAdapter, query!!)

            binding.viewEmpty.visibility = View.GONE
        }
    }


    private fun getData(starAdapter: SearchStarAdapter, query: String) {
        searchViewModel.search(query).observe(this) { stars ->
            if (stars != null) {
                when (stars) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.visibility = View.GONE
                        binding.rvStar.visibility = View.VISIBLE

                        starAdapter.setData(stars.data)
                        starAdapter.notifyDataSetChanged()
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.visibility = View.VISIBLE
                        binding.rvStar.visibility = View.GONE
                    }
                }
            }

            with(binding.rvStar) {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                adapter = starAdapter
            }
        }
    }
}