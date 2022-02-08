package com.test.endtecttest.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.endtecttest.data.model.Item
import com.test.endtecttest.util.loadImage
import endtecttest.R
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val items: ArrayList<Item>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            itemView.product_name.text = item.name
            itemView.product_price.text = item.price
            itemView.image_product.loadImage(item.image, R.color.colorgrey)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(items[position])

    fun addData(list: List<Item>) {
        items.addAll(list)
    }

}