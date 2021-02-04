package com.example.viewmodelcounter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.example.viewmodelcounter.databinding.ActivityMainDatabinding1Binding
import kotlin.random.Random

class MainLiveDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainDatabinding1Binding
    private lateinit var viewModel: CounterViewModel1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
                R.layout.activity_main_databinding1)
        viewModel = ViewModelProvider(this).get(CounterViewModel1::class.java)
        binding.viewModel = viewModel

        binding.plusButton.setOnClickListener {
            Log.d("ZZZ", "plus onclick")
            viewModel.counter.inc()
        }
        binding.minusButton.setOnClickListener {
            Log.d("ZZZ", "minus onclick")
            viewModel.counter.dec()
        }

        viewModel.positiveSum.observe(this, {
            Log.d("ZZZ", "viewModel.positiveSum observed")
            binding.posSum.text = it.toString()
        })
        viewModel.negativeSum.observe(this, {
            Log.d("ZZZ", "viewModel.negativeSum observed")
            binding.negSum.text = it.toString()
        })
    }
}

class CounterViewModel1: ViewModel() {
    val counter = Counter1()
    val positiveSum: LiveData<Int> = Transformations.distinctUntilChanged(
            Transformations.map(counter.ops) { it.filter { it > 0 }.sum() }
    )
    val negativeSum: LiveData<Int> = Transformations.distinctUntilChanged(
            Transformations.map(counter.ops) { it.filter { it < 0 }.sum() }
    )
}

class Counter1(initValue: Int = 0) {
    // mutable live data that contains immutable list
    val ops: MutableLiveData<List<Int>> = MutableLiveData(listOf())
    init {
        Log.d("ZZZ", "counter init")
    }
    fun inc(amount: Int = 1) {
        Log.d("ZZZ", "counter inc()")
        addOp(if(Random.nextBoolean()) amount else 0)
    }
    fun dec(amount: Int = 1) {
        Log.d("ZZZ", "counter dec()")
        addOp(if(Random.nextBoolean()) -amount else 0)
    }
    private fun addOp(amount: Int) {
        // new list is assigned to live data object
        ops.value = ops.value?.plus(listOf(amount))
        Log.d("ZZZ", "addOp() ops: ${ops.value}")
    }
}