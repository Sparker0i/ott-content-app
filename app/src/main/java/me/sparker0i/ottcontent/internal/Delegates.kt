package me.sparker0i.ottcontent.internal

import kotlinx.coroutines.*

fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}

fun <T> deferred(block: suspend CoroutineScope.() -> T): Deferred<T> {
    return GlobalScope.async(start = CoroutineStart.LAZY) {
        block.invoke(this)
    }
}