package com.lowbottgames.au.perth.traffic.cam

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lowbottgames.au.perth.traffic.cam.adapter.PlaceItemsRVAdapter
import com.lowbottgames.au.perth.traffic.cam.databinding.ActivityMainBinding
import com.lowbottgames.au.perth.traffic.cam.domain.CamItem
import com.lowbottgames.au.perth.traffic.cam.domain.PlaceContent
import com.lowbottgames.au.perth.traffic.cam.domain.PlaceItem
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PlaceItemsRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)

        val placeItems = PlaceContent.items
        adapter = PlaceItemsRVAdapter()
        adapter.items = placeItems
        adapter.setOnPlaceItemsRVAdapterListener(object: PlaceItemsRVAdapter.OnPlaceItemsRVAdapterListener {
            override fun onSelectPlaceItem(position: Int, placeItem: PlaceItem) {
                startActivity(
                    CamItemsActivity.newIntent(
                        this@MainActivity,
                        position
                    )
                )
            }

            override fun onSelectCamItem(
                placePosition: Int,
                placeItem: PlaceItem,
                camPosition: Int,
                camItem: CamItem
            ) {
                startActivity(
                    CamItemActivity.newIntent(
                        this@MainActivity,
                        camItem
                    )
                )
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            invalidateImages(placeItems)

            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val flag = sharedPreferences.getBoolean("list_display_images_preference", true)
        adapter.camItemImagesVisible = flag
        adapter.notifyDataSetChanged()
    }

    private fun invalidateImages(placeItems: Array<PlaceItem>) {
        for (placeItem in placeItems) {
            for (camItem in placeItem.camItem) {
                Picasso.get()
                    .invalidate(TCPHelper.getImageURLString(camItem.camID))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                startActivity(SettingsActivity.newIntent(this@MainActivity))
                return true
            }
        }
        return true
    }
}
