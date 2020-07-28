package com.lowbottgames.au.perth.traffic.cam

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lowbottgames.au.perth.traffic.cam.TCPHelper.getImageURLString
import com.lowbottgames.au.perth.traffic.cam.adapter.CamItemsNoImageRVAdapter
import com.lowbottgames.au.perth.traffic.cam.adapter.CamItemsRVAdapter
import com.lowbottgames.au.perth.traffic.cam.databinding.ActivityCamItemsBinding
import com.lowbottgames.au.perth.traffic.cam.domain.CamItem
import com.lowbottgames.au.perth.traffic.cam.domain.PlaceContent
import com.lowbottgames.au.perth.traffic.cam.domain.PlaceItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_place.*

private const val KEY_PLACE_ITEM = "KEY_PLACE_ITEM"

class CamItemsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCamItemsBinding
    private var placeItem: PlaceItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cam_items)

        setSupportActionBar(binding.toolbar)

        intent?.let {
            placeItem = PlaceContent.items[
                intent.getIntExtra(KEY_PLACE_ITEM, 0)
            ]
        }

        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            title = placeItem?.placeName
        }

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val flag = sharedPreferences.getBoolean("list_display_images_preference", true)

        if (flag) {
            val camItemsAdapter = CamItemsRVAdapter()
            camItemsAdapter.items = placeItem?.camItem
            camItemsAdapter.setOnCamItemsRVAdapterListener(object : CamItemsRVAdapter.OnCamItemsRVAdapterListener {
                override fun onSelectCamItem(position: Int, camItem: CamItem) {
                    startActivity(CamItemActivity.newIntent(
                        this@CamItemsActivity,
                        camItem
                    ))
                }
            })

            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE ->
                    recyclerView.layoutManager = GridLayoutManager(this, 3)
                else ->
                    recyclerView.layoutManager = GridLayoutManager(this, 2)
            }
            binding.recyclerView.adapter = camItemsAdapter
        } else {
            val camItemsNoImageRVAdapter = CamItemsNoImageRVAdapter()
            camItemsNoImageRVAdapter.items = placeItem?.camItem
            camItemsNoImageRVAdapter.setOnCamItemsNoImageRVAdapterListener(object : CamItemsNoImageRVAdapter.OnCamItemsNoImageRVAdapterListener {
                override fun onSelectCamItem(position: Int, camItem: CamItem) {
                    startActivity(CamItemActivity.newIntent(
                        this@CamItemsActivity,
                        camItem
                    ))
                }

            })
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = camItemsNoImageRVAdapter
        }
        binding.recyclerView.setHasFixedSize(true)

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            invalidateImages(placeItem)
            binding.recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun invalidateImages(placeItem: PlaceItem?) {
        placeItem?.let {
            for (camItem in it.camItem) {
                Picasso.get()
                    .invalidate(TCPHelper.getImageURLString(camItem.camID))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return true
    }

    companion object {
        fun newIntent(context: Context, index: Int) : Intent {
            val intent = Intent(context, CamItemsActivity::class.java)
            intent.putExtra(KEY_PLACE_ITEM, index)
            return intent
        }
    }

}