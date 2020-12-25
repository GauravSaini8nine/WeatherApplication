package com.example.weatherapplication.Data.Internal

import kotlinx.coroutines.*
import org.kodein.di.Kodein.Companion.lazy

fun <T>lazyDeferred(block:suspend CoroutineScope.()-> T):Lazy<Deferred<T>> {
    return kotlin.lazy{
         GlobalScope.async (start =  CoroutineStart.LAZY){
             block.invoke(this)
         }
    }
}