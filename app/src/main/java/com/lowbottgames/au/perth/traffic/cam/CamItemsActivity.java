package com.lowbottgames.au.perth.traffic.cam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.lowbottgames.au.perth.traffic.cam.adapter.CamItemsNoImageRVAdapter;
import com.lowbottgames.au.perth.traffic.cam.adapter.CamItemsRVAdapter;
import com.lowbottgames.au.perth.traffic.cam.domain.CamItem;
import com.lowbottgames.au.perth.traffic.cam.domain.PlaceContent;
import com.lowbottgames.au.perth.traffic.cam.domain.PlaceItem;
import com.squareup.picasso.Picasso;

public class CamItemsActivity extends AppCompatActivity {

    private static final String KEY_PLACE_ITEM = "KEY_PLACE_ITEM";

    private RecyclerView.Adapter recyclerViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PlaceItem placeItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_items);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int placeItemIndex = 0;

        Intent intent = getIntent();
        if (intent != null) {
            placeItemIndex = intent.getIntExtra(KEY_PLACE_ITEM, 0);
        }

        placeItem = PlaceContent.ITEMS.get(placeItemIndex);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(placeItem.placeName);
        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean flag = sharedPreferences.getBoolean("list_display_images_preference", true);




        if (flag) {
            CamItemsRVAdapter camItemsRVAdapter = new CamItemsRVAdapter();
            camItemsRVAdapter.setItems(placeItem.camItem);
            camItemsRVAdapter.setOnCamItemsRVAdapterListener(new CamItemsRVAdapter.OnCamItemsRVAdapterListener() {
                @Override
                public void onSelectCamItem(int position, CamItem camItem) {
                    startActivity(CamItemActivity.newIntent(CamItemsActivity.this, camItem));
                }
            });
            recyclerViewAdapter = camItemsRVAdapter;

            Configuration configuration = getResources().getConfiguration();
            if (configuration != null && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            }
        } else {

            CamItemsNoImageRVAdapter camItemsNoImageRVAdapter = new CamItemsNoImageRVAdapter();
            camItemsNoImageRVAdapter.setItems(placeItem.camItem);
            camItemsNoImageRVAdapter.setOnCamItemsNoImageRVAdapterListener(new CamItemsNoImageRVAdapter.OnCamItemsNoImageRVAdapterListener() {
                @Override
                public void onSelectCamItem(int position, CamItem camItem) {
                    startActivity(CamItemActivity.newIntent(CamItemsActivity.this, camItem));
                }
            });

            recyclerViewAdapter = camItemsNoImageRVAdapter;

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }


        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setHasFixedSize(true);


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);

                    invalidateImages(placeItem);

                    if (recyclerViewAdapter != null) {
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    private void invalidateImages(final PlaceItem placeItem) {
        if (placeItem != null) {
            for (CamItem camItem: placeItem.camItem) {
                Picasso.get().invalidate(TCPHelper.getImageURLString(camItem.camID));
            }
        }
    }

    public static Intent newIntent(Context context, int index) {
        Intent intent = new Intent(context, CamItemsActivity.class);
        intent.putExtra(KEY_PLACE_ITEM, index);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
    }

}
