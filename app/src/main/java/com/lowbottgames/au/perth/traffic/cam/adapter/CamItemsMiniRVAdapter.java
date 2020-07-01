package com.lowbottgames.au.perth.traffic.cam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lowbottgames.au.perth.traffic.cam.R;
import com.lowbottgames.au.perth.traffic.cam.TCPHelper;
import com.lowbottgames.au.perth.traffic.cam.domain.CamItem;
import com.squareup.picasso.Picasso;

public class CamItemsMiniRVAdapter extends RecyclerView.Adapter<CamItemsMiniRVAdapter.CamItemsRVViewHolder> {

    private CamItem[] items;
    private OnCamItemsMiniRVAdapterListener onCamItemsMiniRVAdapterListener;

    @Override
    public CamItemsRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CamItemsRVViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cam_mini, parent, false));
    }

    @Override
    public void onBindViewHolder(final CamItemsRVViewHolder holder, int position) {
        CamItem item = items[position];

        Context context = holder.itemView.getContext();

        holder.textViewTitle.setText(item.camName);
        holder.textViewSubtitle.setText(item.camDirection);

        String url = TCPHelper.getImageURLString(item.camID);

        Picasso.with(context)
                .load(url)
//                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCamItemsMiniRVAdapterListener != null) {
                    int adapterPosition = holder.getAdapterPosition();
                    onCamItemsMiniRVAdapterListener.onSelectCamItem(adapterPosition, items[adapterPosition]);
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
        ImageView imageView;

        public CamItemsRVViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.textView_title);
            textViewSubtitle = (TextView) itemView.findViewById(R.id.textView_subtitle);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public void setOnCamItemsMiniRVAdapterListener(OnCamItemsMiniRVAdapterListener onCamItemsMiniRVAdapterListener) {
        this.onCamItemsMiniRVAdapterListener = onCamItemsMiniRVAdapterListener;
    }

    public interface OnCamItemsMiniRVAdapterListener {
        void onSelectCamItem(int position, CamItem camItem);
    }

}