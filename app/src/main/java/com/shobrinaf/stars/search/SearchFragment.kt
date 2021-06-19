package com.shobrinaf.stars.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.shobrinaf.stars.core.data.Resource
import com.shobrinaf.stars.core.ui.SearchStarAdapter
import com.shobrinaf.stars.databinding.FragmentSearchBinding
import com.shobrinaf.stars.detail.DetailSearchStarActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private var query: String? = ""
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val starAdapter = SearchStarAdapter()
            starAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailSearchStarActivity::class.java)
                intent.putExtra(DetailSearchStarActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            if (query == "") {
                binding.viewEmpty.root.visibility = View.VISIBLE
                binding.rvStar.rvStarsStaggered.visibility = View.GONE
            }

            binding.btnSearch.setOnClickListener {
                query = binding.inputQuery.editText?.text.toString()
                starAdapter.notifyDataSetChanged()
                getData(starAdapter, query!!)

                binding.viewEmpty.root.visibility = View.GONE
            }
        }
    }

    private fun getData(starAdapter: SearchStarAdapter, query: String) {
        searchViewModel.search(query).observe(viewLifecycleOwner, { stars ->
            when (stars) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.viewError.root.visibility = View.GONE
                    binding.rvStar.rvStarsStaggered.visibility = View.VISIBLE

                    starAdapter.setData(stars.data)
                    starAdapter.notifyDataSetChanged()
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.viewError.root.visibility = View.VISIBLE
                    binding.rvStar.rvStarsStaggered.visibility = View.GONE
                }
            }
        })

        with(binding.rvStar.rvStarsStaggered) {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

            adapter = starAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
