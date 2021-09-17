package com.example.viewmodelcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelcounter.databinding.ActivityMainBinding

class MainActivityViewModelLiveData : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: LiveCounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LiveCounterViewModel::class.java)

        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.plusButton.setOnClickListener {
            viewModel.counter.inc()
        }
        binding.minusButton.setOnClickListener {
            viewModel.counter.dec()
        }
        viewModel.counter.value.observe(this, Observer {
            Log.i("COUNTER", "observe")
            binding.valueView.text = viewModel.counter.value?.value.toString()
        })
    }

    private fun updateUI() {
        binding.valueView.text = viewModel.counter.value.toString()
    }
}

class LiveCounterViewModel: ViewModel() {
    val counter = LiveCounter()
}

class LiveCounter(initValue: Int = 0) {
    var value: MutableLiveData<Int> = MutableLiveData()
        private set
    init {
        value.value = initValue
    }
    fun inc() { value.value = value.value?.plus(1) ?: 0}
    fun dec() { value.value = value.value?.plus(-1) ?: 0 }
}
