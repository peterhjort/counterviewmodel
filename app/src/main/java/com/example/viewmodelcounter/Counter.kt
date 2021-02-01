package com.example.viewmodelcounter

class Counter(initValue: Int = 0) {
    var value: Int = initValue
        private set
    fun inc() { value++ }
    fun dec() { value-- }
}
