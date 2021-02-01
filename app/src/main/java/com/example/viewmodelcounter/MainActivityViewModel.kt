package com.example.viewmodelcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelcounter.databinding.ActivityMainBinding

class MainActivityViewModel : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CounterViewModel::class.java)

        binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.plusButton.setOnClickListener {
            viewModel.counter.inc()
            updateUI()
        }
        binding.minusButton.setOnClickListener {
            viewModel.counter.dec()
            updateUI()
        }
        updateUI()
    }

    private fun updateUI() {
        binding.valueView.text = viewModel.counter.value.toString()
    }
}

class CounterViewModel: ViewModel() {
    val counter = Counter()
}