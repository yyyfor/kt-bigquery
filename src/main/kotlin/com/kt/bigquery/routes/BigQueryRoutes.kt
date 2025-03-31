package com.kt.bigquery.routes

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import com.kt.bigquery.config.AppConfig
import com.google.cloud.bigquery.BigQuery
import com.google.cloud.bigquery.BigQueryOptions
import com.google.cloud.bigquery.QueryJobConfiguration

fun Application.configureRouting(config: AppConfig) {
    routing {
        get("/query") {
            val bigquery = BigQueryOptions.newBuilder()
                .setProjectId(config.projectId)
                .build()
                .service

            val query = "SELECT * FROM `${config.projectId}.${config.datasetId}.your_table` LIMIT 10"
            val queryConfig = QueryJobConfiguration.newBuilder(query).build()
            
            val results = bigquery.query(queryConfig)
            val rows = results.iterateAll().map { row ->
                row.toString()
            }
            
            call.respond(rows)
        }
    }
}