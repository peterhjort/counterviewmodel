package com.example.viewmodelcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.viewmodelcounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val counter = Counter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.plusButton.setOnClickListener {
            counter.inc()
            updateUI()
        }
        binding.minusButton.setOnClickListener {
            counter.dec()
            updateUI()
        }
        updateUI()
    }

    private fun updateUI() {
        binding.valueView.text = counter.value.toString()
    }
}

