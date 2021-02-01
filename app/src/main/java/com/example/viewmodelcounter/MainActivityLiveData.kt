package com.example.viewmodelcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelcounter.databinding.ActivityMainBinding

class MainActivityLiveData : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: CounterViewModel1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CounterViewModel1::class.java)

        binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.plusButton.setOnClickListener {
            viewModel.counter.inc()
        }
        binding.minusButton.setOnClickListener {
            viewModel.counter.dec()
        }
        viewModel.counter.number.observe(this, {
            Log.d("ZZZ", "viewModel.counter.number observed")
            binding.valueView.text = it.toString()
        })
    }
}

class CounterViewModel1: ViewModel() {
    val counter = Counter1()
}

class Counter1(initValue: Int = 0) {
    var number: MutableLiveData<Int> = MutableLiveData()
        private set
    init {
        Log.d("ZZZ", "counter init");
        number.value = initValue
    }
    fun inc() {
        Log.d("ZZZ", "counter inc()");
        number.value = number.value?.plus(1)
    }
    fun dec() {
        Log.d("ZZZ", "counter dec()");
        number.value = number.value?.minus(1)
    }
}