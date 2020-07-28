package com.lowbottgames.au.perth.traffic.cam

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lowbottgames.au.perth.traffic.cam.TCPHelper.getImageURLString
import com.lowbottgames.au.perth.traffic.cam.databinding.ActivityCamItemBinding
import com.lowbottgames.au.perth.traffic.cam.domain.CamItem
import com.squareup.picasso.Picasso


private const val KEY_CAM_DIRECTION = "KEY_CAM_DIRECTION"
private const val KEY_CAM_ID = "KEY_CAM_ID"
private const val KEY_CAM_INFO = "KEY_CAM_INFO"
private const val KEY_CAM_NAME = "KEY_CAM_NAME"

class CamItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCamItemBinding
    private var camDirection: String? = null
    private var camId: String? = null
    private var camInfo: String? = null
    private var camName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cam_item)

        setSupportActionBar(binding.toolbar)

        if (intent != null) {
            camDirection = intent.getStringExtra(KEY_CAM_DIRECTION)
            camId = intent.getStringExtra(KEY_CAM_ID)
            camInfo = intent.getStringExtra(KEY_CAM_INFO)
            camName = intent.getStringExtra(KEY_CAM_NAME)
        }

        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            title = camName
            subtitle = camDirection
        }

        binding.textView.text = camInfo

        loadImage(imageView = binding.imageView, id = camId)


        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            Picasso.get()
                .invalidate(TCPHelper.getImageURLString(camId!!))
            loadImage(binding.imageView, camId)
        }
    }

    private fun loadImage(imageView: ImageView, id: String?) {
        id?.let {
            Picasso.get()
                .load(TCPHelper.getImageURLString(it))
                .into(imageView)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cam_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                camId?.let {
                    Picasso.get()
                        .invalidate(TCPHelper.getImageURLString(it))
                    loadImage(binding.imageView, it)
                }
                return true
            }
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return true
    }

    companion object {
        fun newIntent(context: Context, item: CamItem) : Intent {
            val intent = Intent(context, CamItemActivity::class.java)
            intent.putExtra(KEY_CAM_DIRECTION, item.camDirection);
            intent.putExtra(KEY_CAM_ID, item.camID);
            intent.putExtra(KEY_CAM_INFO, item.camInfo);
            intent.putExtra(KEY_CAM_NAME, item.camName);
            return intent
        }
    }
}