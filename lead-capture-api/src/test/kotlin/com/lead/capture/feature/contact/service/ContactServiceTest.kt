package com.lead.capture.feature.contact.service

import com.lead.capture.common.SNSProducer
import com.lead.capture.feature.contact.model.Contact
import com.lead.capture.feature.contact.repository.ContactRepository
import com.lead.capture.model.ContactResponse
import com.lead.capture.model.CreateNewContact
import io.mockk.*
import org.apache.logging.log4j.Logger
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

class ContactServiceTest {

    private lateinit var contactService: ContactService
    private val contactRepository: ContactRepository = mockk()
    private val contactMapper: ContactMapper = mockk()
    private val snsProducer: SNSProducer = mockk()
    private val log: Logger = mockk()

    @BeforeEach
    fun setUp() {
        contactService = ContactService(contactRepository, contactMapper, snsProducer)
    }

    @Test
    fun `saveContact should update existing contact`() {
        val contact = createContactMock()
        val existingContact = mockk<Contact>(relaxed = true)

        every { contactRepository.findByEmail(contact.email) } returns existingContact
        every { contactRepository.save(existingContact) } returns existingContact

        contactService.saveContact(contact)

        verify { existingContact.requestCount += 1 }
        verify { contactRepository.save(existingContact) }
    }

    @Test
    fun `saveContact should create a new contact if not exists`() {
        val contact = createContactMock()
        val newContact = mockk<Contact>(relaxed = true)

        every { contactRepository.findByEmail(contact.email) } returns null
        every { contactMapper.toInternal(contact) } returns newContact
        every { contactRepository.save(newContact) } returns  newContact
        every { snsProducer.sendSnSMessage(any()) } just Runs

        contactService.saveContact(contact)

        verify { contactRepository.save(newContact) }
        verify { snsProducer.sendSnSMessage(newContact.toString()) }
    }

    @Test
    fun `getContactByEmail should return mapped contact`() {
        val email = "test@example.com"
        val contact = mockk<Contact>(relaxed = true)
        val responseContact = mockk<ContactResponse>()

        every { contactRepository.findByEmail(email) } returns contact
        every { contactMapper.toResponse(contact) } returns responseContact

        val result = contactService.getContactByEmail(email)

        assertNotNull(result)
        verify { contactMapper.toResponse(contact) }
    }


    fun createContactMock(): CreateNewContact {
        return CreateNewContact.builder().id(randomUUID()).email("murilodourado@gmail.com").build();
    }
}
