package com.lead.capture

import com.lead.capture.common.SNSProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(SNSProperties::class)
class CaptureApplication

fun main(args: Array<String>) {
	runApplication<CaptureApplication>(*args)
}
