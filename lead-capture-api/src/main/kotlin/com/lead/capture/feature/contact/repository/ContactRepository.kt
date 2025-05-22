package com.lead.capture.feature.contact.repository

import com.lead.capture.feature.contact.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ContactRepository : JpaRepository<Contact, UUID> {
    fun findByEmail(email: String): Contact?
}

