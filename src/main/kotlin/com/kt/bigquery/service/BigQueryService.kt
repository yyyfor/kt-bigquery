package com.kt.bigquery.service

import com.google.cloud.bigquery.*
import kotlinx.coroutines.withContext
import com.kt.bigquery.config.AppConfig
import com.kt.bigquery.config.DispatcherConfig

class BigQueryService(private val config: AppConfig) {
    private val bigquery: BigQuery = BigQueryOptions.newBuilder()
        .setProjectId(config.projectId)
        .build()
        .service

    suspend fun executeQuery(query: String): List<String> = withContext(DispatcherConfig.getBigQueryDispatcher()) {
        val queryConfig = QueryJobConfiguration.newBuilder(query).build()
        bigquery.query(queryConfig).iterateAll().map { it.toString() }
    }

    suspend fun executeParallelQueries(queries: List<String>): List<List<String>> = 
        withContext(DispatcherConfig.getBigQueryDispatcher()) {
            queries.map { queryString ->
                val queryConfig = QueryJobConfiguration.newBuilder(queryString).build()
                bigquery.query(queryConfig).iterateAll().map { it.toString() }
            }
        }
}