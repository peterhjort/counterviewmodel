package com.example.viewmodelcounter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelcounter.databinding.ActivityMainDatabinding2Binding
import com.example.viewmodelcounter.databinding.ActivityMainDatabindingBinding

class MainLiveDataActivityWithOnClickListeners : AppCompatActivity() {
    lateinit var binding: ActivityMainDatabinding2Binding
    lateinit var viewModel: CounterViewModel2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainDatabinding2Binding>(this,
            R.layout.activity_main_databinding2)
        viewModel = ViewModelProvider(this).get(CounterViewModel2::class.java)

        viewModel.counter.number.observe(this, {
            Log.d("ZZZ", "viewModel.counter.number observed")
            binding.valueView.text = it.toString()
        })

        binding.plusButton.setOnClickListener {
            viewModel.counter.inc()
        }
        binding.minusButton.setOnClickListener {
            viewModel.counter.dec()
        }
    }
}

class CounterViewModel2: ViewModel() {
    val counter = Counter2()
}

class Counter2(initValue: Int = 0) {
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