package com.shobrinaf.stars.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shobrinaf.stars.core.R
import com.shobrinaf.stars.core.databinding.ItemListStarsBinding
import com.shobrinaf.stars.core.domain.model.Stars

class FavoriteStarAdapter : RecyclerView.Adapter<FavoriteStarAdapter.ListViewHolder>() {

    private var listData = ArrayList<Stars>()
    var onItemClick: ((Stars) -> Unit)? = null

    fun setData(newListData: List<Stars>?) {
        listData.clear()
        newListData?.let { listData.addAll(it) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_stars, parent, false)
        )

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListStarsBinding.bind(itemView)
        fun bind(data: Stars) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.url)
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemDate.text = data.date
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}