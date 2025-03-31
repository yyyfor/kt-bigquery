package com.kt.bigquery.config

import io.ktor.server.config.*

data class AppConfig(
    val projectId: String,
    val datasetId: String,
) {
    companion object {
        fun load(config: ApplicationConfig): AppConfig {
            return AppConfig(
                projectId = config.property("bigquery.projectId").getString(),
                datasetId = config.property("bigquery.datasetId").getString()
            )
        }
    }
}