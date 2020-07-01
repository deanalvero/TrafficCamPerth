package com.lowbottgames.au.perth.traffic.cam;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.lowbottgames.au.perth.traffic.cam.adapter.PlaceItemsRVAdapter;
import com.lowbottgames.au.perth.traffic.cam.domain.CamItem;
import com.lowbottgames.au.perth.traffic.cam.domain.PlaceContent;
import com.lowbottgames.au.perth.traffic.cam.domain.PlaceItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PlaceItemsRVAdapter placeItemsRVAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<PlaceItem> placeItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        placeItems = PlaceContent.ITEMS;
        placeItemsRVAdapter = new PlaceItemsRVAdapter();
        placeItemsRVAdapter.setItems(placeItems);
        placeItemsRVAdapter.setOnPlaceItemsRVAdapterListener(new PlaceItemsRVAdapter.OnPlaceItemsRVAdapterListener() {
            @Override
            public void onSelectPlaceItem(int position, PlaceItem placeItem) {
                startActivity(CamItemsActivity.newIntent(MainActivity.this, position));
            }

            @Override
            public void onSelectCamItem(int placePosition, PlaceItem placeItem, int camPosition, CamItem camItem) {
                startActivity(CamItemActivity.newIntent(MainActivity.this, camItem));
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(placeItemsRVAdapter);
        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);

                    invalidateImages(placeItems);

                    if (placeItemsRVAdapter != null) {
                        placeItemsRVAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void invalidateImages(final List<PlaceItem> placeItems) {
        if (placeItems != null) {
            for (PlaceItem placeItem: placeItems) {
                for (CamItem camItem: placeItem.camItem) {
                    Picasso.get().invalidate(TCPHelper.getImageURLString(camItem.camID));
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean flag = sharedPreferences.getBoolean("list_display_images_preference", true);

        if (placeItemsRVAdapter != null) {
            placeItemsRVAdapter.setShowCamItemImages(flag);
            placeItemsRVAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(SettingsActivity.newIntent(this));
                return true;
        }
        return true;
    }

}
