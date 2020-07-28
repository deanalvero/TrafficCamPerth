package com.lowbottgames.au.perth.traffic.cam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lowbottgames.au.perth.traffic.cam.R
import com.lowbottgames.au.perth.traffic.cam.databinding.ItemPlaceBinding
import com.lowbottgames.au.perth.traffic.cam.domain.CamItem
import com.lowbottgames.au.perth.traffic.cam.domain.PlaceItem

class PlaceItemsRVAdapter : RecyclerView.Adapter<PlaceItemsRVAdapter.PlaceItemsRVViewHolder>() {

    var items: Array<PlaceItem>? = null
    var camItemImagesVisible = false

    private var listener: OnPlaceItemsRVAdapterListener? = null

    private val recycledViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    init {
        recycledViewPool.setMaxRecycledViews(0, 25)
    }

    class PlaceItemsRVViewHolder(val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceItemsRVViewHolder {
        val binding: ItemPlaceBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_place,
            parent,
            false
        )
        return PlaceItemsRVViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: PlaceItemsRVViewHolder, position: Int) {
        val item = items!![position]

        holder.binding.textView.text = item.placeName

        val adapter = CamItemsMiniRVAdapter()
        adapter.items = item.camItem
        adapter.setOnCamItemsMiniRVAdapterListener(object: CamItemsMiniRVAdapter.OnCamItemsMiniRVAdapterListener {
            override fun onSelectCamItem(position: Int, camItem: CamItem) {
                listener?.run {
                    onSelectCamItem(
                        holder.adapterPosition,
                        items!![holder.adapterPosition],
                        position,
                        camItem
                    )
                }
            }
        })

        if (camItemImagesVisible) {
            holder.binding.recyclerView.visibility = View.VISIBLE
            holder.binding.recyclerView.layoutManager = LinearLayoutManager(
                holder.itemView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            holder.binding.recyclerView.adapter = adapter
            holder.binding.recyclerView.setHasFixedSize(true)
            holder.binding.recyclerView.setRecycledViewPool(recycledViewPool)
        } else {
            holder.binding.recyclerView.visibility = View.GONE
        }

        holder.binding.relativeLayout.setOnClickListener {
            listener?.run {
                onSelectPlaceItem(
                    holder.adapterPosition,
                    items!![holder.adapterPosition]
                )
            }
        }
    }

    fun setOnPlaceItemsRVAdapterListener(listener: OnPlaceItemsRVAdapterListener) {
        this.listener = listener
    }

    interface OnPlaceItemsRVAdapterListener {
        fun onSelectPlaceItem(position: Int, placeItem: PlaceItem)
        fun onSelectCamItem(placePosition: Int, placeItem: PlaceItem, camPosition: Int, camItem: CamItem)
    }

}