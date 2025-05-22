package com.lead.capture.common

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Component
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region.US_EAST_1
import software.amazon.awssdk.services.sns.SnsClient
import java.net.URI

@Component
class SNSProducer(val snsProperties: SNSProperties) {

    val LOCALSTACK_URL = "http://localhost:4566"
    val log: Logger = LogManager.getLogger()

    fun sendSnSMessage(message: String) {
        SnsClient.builder().region(US_EAST_1)
            .endpointOverride(URI.create(LOCALSTACK_URL))
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(snsProperties.credentials.accessKey, snsProperties.credentials.secretKey)
                )
            )
            .build().use { snsClient ->
            snsClient.publish {
                builder -> builder.topicArn(snsProperties.sns.topic.arn).message(message)
                log.info(message)
            }
        }
    }
}