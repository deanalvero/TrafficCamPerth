package com.lowbottgames.au.perth.traffic.cam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lowbottgames.au.perth.traffic.cam.R
import com.lowbottgames.au.perth.traffic.cam.TCPHelper
import com.lowbottgames.au.perth.traffic.cam.databinding.ItemCamMiniBinding
import com.lowbottgames.au.perth.traffic.cam.domain.CamItem
import com.squareup.picasso.Picasso

class CamItemsMiniRVAdapter : RecyclerView.Adapter<CamItemsMiniRVAdapter.CamItemsRVViewHolder>() {

    var items: Array<CamItem>? = null
    private var listener: OnCamItemsMiniRVAdapterListener? = null

    class CamItemsRVViewHolder(val binding: ItemCamMiniBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CamItemsRVViewHolder {
        val binding: ItemCamMiniBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_cam_mini,
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

        val urlString = TCPHelper.getImageURLString(item.camID)

        Picasso.get()
            .load(urlString)
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

    fun setOnCamItemsMiniRVAdapterListener(listener: OnCamItemsMiniRVAdapterListener) {
        this.listener = listener
    }

    interface OnCamItemsMiniRVAdapterListener {
        fun onSelectCamItem(position: Int, camItem: CamItem)
    }
}