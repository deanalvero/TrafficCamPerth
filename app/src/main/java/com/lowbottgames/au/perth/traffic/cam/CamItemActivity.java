package com.lowbottgames.au.perth.traffic.cam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.lowbottgames.au.perth.traffic.cam.domain.CamItem;
import com.squareup.picasso.Picasso;

public class CamItemActivity extends AppCompatActivity {

    private static final String KEY_CAM_DIRECTION = "KEY_CAM_DIRECTION";
    private static final String KEY_CAM_ID = "KEY_CAM_ID";
    private static final String KEY_CAM_INFO = "KEY_CAM_INFO";
    private static final String KEY_CAM_NAME = "KEY_CAM_NAME";

    private SwipeRefreshLayout swipeRefreshLayout;

    private TextView textView;
    private ImageView imageView;
    private String camDirection;
    private String camId;
    private String camInfo;
    private String camName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent != null) {
            camDirection = intent.getStringExtra(KEY_CAM_DIRECTION);
            camId = intent.getStringExtra(KEY_CAM_ID);
            camInfo = intent.getStringExtra(KEY_CAM_INFO);
            camName = intent.getStringExtra(KEY_CAM_NAME);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(camName);
            actionBar.setSubtitle(camDirection);
        }

        imageView = (ImageView) findViewById(R.id.imageView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        textView = (TextView) findViewById(R.id.textView);

        textView.setText(camInfo);

        loadImage(imageView, camId);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);

                    Picasso.get().invalidate(TCPHelper.getImageURLString(camId));
                    loadImage(imageView, camId);
                }
            }
        });
    }

    private void loadImage(ImageView imageView, String id) {
        if (imageView != null && camId != null) {
            Picasso.get()
                    .load(TCPHelper.getImageURLString(id))
//                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(imageView);
        }
    }

    public static Intent newIntent(Context context, CamItem item) {
        Intent intent = new Intent(context, CamItemActivity.class);
        intent.putExtra(KEY_CAM_DIRECTION, item.camDirection);
        intent.putExtra(KEY_CAM_ID, item.camID);
        intent.putExtra(KEY_CAM_INFO, item.camInfo);
        intent.putExtra(KEY_CAM_NAME, item.camName);
        return intent;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cam_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                Picasso.get().invalidate(TCPHelper.getImageURLString(camId));
                loadImage(imageView, camId);
                return true;

            case android.R.id.home:
                finish();
                return true;
        }
        return true;
    }
}
