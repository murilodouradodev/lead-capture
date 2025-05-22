package com.lead.capture.common

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cloud.aws")
data class SNSProperties(
    val credentials: Credentials,
    val sns: Sns
) {
    data class Credentials(
        val accessKey: String,
        val secretKey: String
    )

    data class Sns(
        val topic: Topic
    ) {
        data class Topic(
            val arn: String
        )
    }
}