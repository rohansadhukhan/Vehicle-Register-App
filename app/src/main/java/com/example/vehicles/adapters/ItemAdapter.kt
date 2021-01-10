package com.example.vehicles.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vehicles.R
import kotlinx.android.synthetic.main.item.view.*

class ItemAdapter(
    private val context: Context,
    private val items: List<String>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ItemAdapter.CustomViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }

    class CustomViewHolder(
        itemView: View,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClicked(adapterPosition)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder =
        CustomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false),
            listener
        )

    override fun onBindViewHolder(
        holder: CustomViewHolder,
        position: Int
    ) {
        var item: String = items[position]
        holder.itemView.apply {
            itemName.text = item
        }
    }

    override fun getItemCount(): Int = items.size
}