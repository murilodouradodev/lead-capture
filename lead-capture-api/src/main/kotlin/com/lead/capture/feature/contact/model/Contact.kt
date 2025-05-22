package com.lead.capture.feature.contact.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.CreatedDate
import java.time.OffsetDateTime
import java.util.*

@Entity
data class Contact(
    @Id
    val id: UUID,
    val name: String,
    val email: String,
    val phone: String,
    val source: String,
    var requestCount: Int = 1,
    val createdAt: OffsetDateTime )
