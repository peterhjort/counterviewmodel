package com.example.viewmodelcounter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelcounter.databinding.ActivityMainDatabindingBinding

class MainLiveDataActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainDatabindingBinding
    lateinit var viewModel: CounterViewModel1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainDatabindingBinding>(this,
                R.layout.activity_main_databinding)
        viewModel = ViewModelProvider(this).get(CounterViewModel1::class.java)
        binding.viewModel = viewModel

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
        Log.d("ZZZ", "counter init")
        number.value = initValue
    }
    fun inc() {
        Log.d("ZZZ", "counter inc()")
        number.value = number.value?.plus(1)
    }
    fun dec() {
        Log.d("ZZZ", "counter dec()")
        number.value = number.value?.minus(1)
    }
}