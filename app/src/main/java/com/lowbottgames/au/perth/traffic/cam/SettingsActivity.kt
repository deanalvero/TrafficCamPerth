package com.lowbottgames.au.perth.traffic.cam

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lowbottgames.au.perth.traffic.cam.databinding.ActivitySettingsBinding
import com.lowbottgames.au.perth.traffic.cam.fragment.SettingsFragment
import com.squareup.picasso.Picasso

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.action_settings)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, SettingsFragment())
            .commitAllowingStateLoss()
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
        fun newIntent(context: Context) : Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }
}