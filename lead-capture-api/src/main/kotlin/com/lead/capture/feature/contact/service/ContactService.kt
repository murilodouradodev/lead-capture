package com.lead.capture.feature.contact.service

import com.lead.capture.common.SNSProducer
import com.lead.capture.feature.contact.repository.ContactRepository
import com.lead.capture.model.CreateNewContact
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Service

@Service
class ContactService(
    private val contactRepository: ContactRepository,
    private val contactMapper: ContactMapper,
    private val snsProducer: SNSProducer) {

    val log: Logger = LogManager.getLogger()

    fun saveContact(contact: CreateNewContact) {
        val existingContact = contactRepository.findByEmail(contact.email)?.apply {
            requestCount += 1
            contactRepository.save(this)
        }

        if (existingContact == null) {
            val newContact = contactMapper.toInternal(contact).apply { requestCount = 1 }
            try {
                contactRepository.save(newContact)
            } catch (e: Exception) {
                log.error("Failed to save contact: ${newContact.email}", e)
                throw e
            } finally {
                snsProducer.sendSnSMessage(newContact.toString())
            }
        }
    }

    fun getContactByEmail(email: String) = contactRepository.findByEmail(email)?.let { contactMapper.toResponse(it) }
}