package com.shobrinaf.stars.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.shobrinaf.stars.core.data.Resource
import com.shobrinaf.stars.core.ui.StarAdapter
import com.shobrinaf.stars.databinding.FragmentHomeBinding
import com.shobrinaf.stars.detail.DetailStarActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val starAdapter = StarAdapter()
            starAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailStarActivity::class.java)
                intent.putExtra(DetailStarActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            getDataAPOD(starAdapter)
        }
    }

    private fun getDataAPOD(starAdapter: StarAdapter) {
        homeViewModel.stars.observe(viewLifecycleOwner, { stars ->
            when (stars) {
                is Resource.Loading -> {
                    loading(true)
                    binding.rvStar.buttonLoad.visibility = View.GONE
                }
                is Resource.Success -> {
                    loading(false)
                    binding.rvStar.buttonLoad.visibility = View.VISIBLE

                    starAdapter.setLimit(5)
                    starAdapter.setData(stars.data)
                    starAdapter.notifyDataSetChanged()

                    val itemCount = starAdapter.itemCount
                    var totalItem = itemCount

                    binding.rvStar.buttonLoad.setOnClickListener { button ->
                        if (totalItem < 50) {
                            totalItem += 5
                            starAdapter.setLimit(totalItem)
                            starAdapter.setData(stars.data)
                            starAdapter.notifyDataSetChanged()

                            button.visibility = View.VISIBLE
                        } else {
                            Toast.makeText(
                                context, "There isn't any left data",
                                Toast.LENGTH_SHORT
                            ).show()
                            button.visibility = View.GONE
                        }
                    }
                }
                is Resource.Error -> {
                    loading(false)
                    binding.viewError.root.visibility = View.VISIBLE
                }
            }
        })

        with(binding.rvStar.rvStars) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = starAdapter
        }
    }

    private fun loading(state: Boolean) {
        if (state == true) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
