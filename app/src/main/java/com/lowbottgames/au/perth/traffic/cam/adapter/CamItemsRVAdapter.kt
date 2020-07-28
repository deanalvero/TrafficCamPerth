package com.lowbottgames.au.perth.traffic.cam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lowbottgames.au.perth.traffic.cam.R
import com.lowbottgames.au.perth.traffic.cam.TCPHelper
import com.lowbottgames.au.perth.traffic.cam.databinding.ItemCamBinding
import com.lowbottgames.au.perth.traffic.cam.domain.CamItem
import com.squareup.picasso.Picasso

class CamItemsRVAdapter : RecyclerView.Adapter<CamItemsRVAdapter.CamItemsRVViewHolder>() {

    var items: Array<CamItem>? = null
    private var listener: OnCamItemsRVAdapterListener? = null

    class CamItemsRVViewHolder(val binding: ItemCamBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CamItemsRVViewHolder {
        val binding: ItemCamBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_cam,
            parent,
            false
        )
        return CamItemsRVViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: CamItemsRVViewHolder, position: Int) {
        val item = items!![position]

        holder.binding.textViewTitle.text = item.camName
        holder.binding.textViewSubtitle.text = item.camDirection

        Picasso.get()
            .load(TCPHelper.getImageURLString(item.camID))
            .into(holder.binding.imageView)

        holder.itemView.setOnClickListener {
            listener?.run {
                onSelectCamItem(
                    holder.adapterPosition,
                    items!![holder.adapterPosition]
                )
            }
        }
    }

    fun setOnCamItemsRVAdapterListener(listener: OnCamItemsRVAdapterListener) {
        this.listener = listener
    }

    interface OnCamItemsRVAdapterListener {
        fun onSelectCamItem(position: Int, camItem: CamItem)
    }

}