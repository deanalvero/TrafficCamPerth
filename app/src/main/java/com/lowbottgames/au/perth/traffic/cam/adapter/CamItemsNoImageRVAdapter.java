package com.lowbottgames.au.perth.traffic.cam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lowbottgames.au.perth.traffic.cam.R;
import com.lowbottgames.au.perth.traffic.cam.domain.CamItem;

public class CamItemsNoImageRVAdapter extends RecyclerView.Adapter<CamItemsNoImageRVAdapter.CamItemsRVViewHolder> {

    private CamItem[] items;
    private OnCamItemsNoImageRVAdapterListener onCamItemsNoImageRVAdapterListener;

    @Override
    public CamItemsRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CamItemsRVViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cam_no_image, parent, false));
    }

    @Override
    public void onBindViewHolder(final CamItemsRVViewHolder holder, int position) {
        final CamItem item = items[position];

        holder.textViewTitle.setText(item.camName);
        holder.textViewSubtitle.setText(item.camDirection);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCamItemsNoImageRVAdapterListener != null) {
                    int adapterPosition = holder.getAdapterPosition();
                    onCamItemsNoImageRVAdapterListener.onSelectCamItem(adapterPosition, items[adapterPosition]);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.length;
    }

    public void setItems(CamItem[] items) {
        this.items = items;
    }

    static class CamItemsRVViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewSubtitle;

        public CamItemsRVViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.textView_title);
            textViewSubtitle = (TextView) itemView.findViewById(R.id.textView_subtitle);
        }
    }

    public void setOnCamItemsNoImageRVAdapterListener(OnCamItemsNoImageRVAdapterListener onCamItemsNoImageRVAdapterListener) {
        this.onCamItemsNoImageRVAdapterListener = onCamItemsNoImageRVAdapterListener;
    }

    public interface OnCamItemsNoImageRVAdapterListener {
        void onSelectCamItem(int position, CamItem camItem);
    }
}