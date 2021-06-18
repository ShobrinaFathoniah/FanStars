package com.shobrinaf.stars.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shobrinaf.stars.core.R
import com.shobrinaf.stars.core.databinding.ItemSearchStarsBinding
import com.shobrinaf.stars.core.domain.model.Stars

class SearchStarAdapter : RecyclerView.Adapter<SearchStarAdapter.ListViewHolder>() {

    private var listData = ArrayList<Stars>()
    var onItemClick: ((Stars) -> Unit)? = null

    fun setData(newListData: List<Stars>?) {
        listData.clear()
        newListData?.let { listData.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_stars, parent, false)
        )

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSearchStarsBinding.bind(itemView)
        fun bind(data: Stars) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.url)
                    .into(ivItemImage)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}