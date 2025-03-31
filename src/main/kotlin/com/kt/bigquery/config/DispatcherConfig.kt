package com.kt.bigquery.config

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

object DispatcherConfig {
    private val bigQueryDispatcher = Executors.newFixedThreadPool(10)
        .asCoroutineDispatcher()

    fun getBigQueryDispatcher(): CoroutineContext = bigQueryDispatcher
}