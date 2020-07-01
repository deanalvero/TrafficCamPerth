package com.lowbottgames.au.perth.traffic.cam.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lowbottgames.au.perth.traffic.cam.R;
import com.lowbottgames.au.perth.traffic.cam.domain.CamItem;
import com.lowbottgames.au.perth.traffic.cam.domain.PlaceItem;

import java.util.List;

public class PlaceItemsRVAdapter extends RecyclerView.Adapter<PlaceItemsRVAdapter.PlaceItemsRVViewHolder> {

    private List<PlaceItem> items;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private OnPlaceItemsRVAdapterListener onPlaceItemsRVAdapterListener;
    private boolean showCamItemImages;

    public PlaceItemsRVAdapter() {
        recycledViewPool = new RecyclerView.RecycledViewPool();
        recycledViewPool.setMaxRecycledViews(0, 25);
    }

    @Override
    public PlaceItemsRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlaceItemsRVViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false));
    }

    @Override
    public void onBindViewHolder(final PlaceItemsRVViewHolder holder, int position) {
        PlaceItem item = items.get(position);

        Context context = holder.itemView.getContext();

        holder.textView.setText(item.placeName);

        CamItemsMiniRVAdapter camItemsRVAdapter = new CamItemsMiniRVAdapter();
        camItemsRVAdapter.setItems(item.camItem);
        camItemsRVAdapter.setOnCamItemsMiniRVAdapterListener(new CamItemsMiniRVAdapter.OnCamItemsMiniRVAdapterListener() {
            @Override
            public void onSelectCamItem(int position, CamItem camItem) {
                if (onPlaceItemsRVAdapterListener != null) {
                    int adapterPosition = holder.getAdapterPosition();
                    onPlaceItemsRVAdapterListener.onSelectCamItem(adapterPosition, items.get(adapterPosition), position, camItem);
                }
            }
        });

        if (showCamItemImages) {
            holder.recyclerView.setVisibility(View.VISIBLE);
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            holder.recyclerView.setAdapter(camItemsRVAdapter);
            holder.recyclerView.setHasFixedSize(true);
            holder.recyclerView.setRecycledViewPool(recycledViewPool);
        } else {
            holder.recyclerView.setVisibility(View.GONE);
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPlaceItemsRVAdapterListener != null) {
                    int adapterPosition = holder.getAdapterPosition();
                    onPlaceItemsRVAdapterListener.onSelectPlaceItem(adapterPosition, items.get(adapterPosition));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public void setItems(List<PlaceItem> items) {
        this.items = items;
    }

    public void setShowCamItemImages(boolean showCamItemImages) {
        this.showCamItemImages = showCamItemImages;
    }

    static class PlaceItemsRVViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        RelativeLayout relativeLayout;
        RecyclerView recyclerView;

        public PlaceItemsRVViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
        }
    }

    public void setOnPlaceItemsRVAdapterListener(OnPlaceItemsRVAdapterListener onPlaceItemsRVAdapterListener) {
        this.onPlaceItemsRVAdapterListener = onPlaceItemsRVAdapterListener;
    }

    public interface OnPlaceItemsRVAdapterListener {
        void onSelectPlaceItem(int position, PlaceItem placeItem);
        void onSelectCamItem(int placePosition, PlaceItem placeItem, int camPosition, CamItem camItem);
    }

}
