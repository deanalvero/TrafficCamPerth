package com.lowbottgames.au.perth.traffic.cam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lowbottgames.au.perth.traffic.cam.R
import com.lowbottgames.au.perth.traffic.cam.databinding.ItemCamNoImageBinding
import com.lowbottgames.au.perth.traffic.cam.domain.CamItem

class CamItemsNoImageRVAdapter : RecyclerView.Adapter<CamItemsNoImageRVAdapter.CamItemsNoImageRVViewHolder>() {

    var items: Array<CamItem>? = null
    private var listener: OnCamItemsNoImageRVAdapterListener? = null

    class CamItemsNoImageRVViewHolder(val binding: ItemCamNoImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CamItemsNoImageRVViewHolder {
        val binding: ItemCamNoImageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_cam_no_image,
            parent,
            false
        )
        return CamItemsNoImageRVViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: CamItemsNoImageRVViewHolder, position: Int) {
        val item = items!![position]

        holder.binding.textViewTitle.text = item.camName
        holder.binding.textViewSubtitle.text = item.camDirection

        holder.itemView.setOnClickListener {
            listener?.run {
                onSelectCamItem(
                    holder.adapterPosition,
                    items!![holder.adapterPosition]
                )
            }
        }
    }

    fun setOnCamItemsNoImageRVAdapterListener(listener: OnCamItemsNoImageRVAdapterListener) {
        this.listener = listener
    }

    interface OnCamItemsNoImageRVAdapterListener {
        fun onSelectCamItem(position: Int, camItem: CamItem)
    }

}