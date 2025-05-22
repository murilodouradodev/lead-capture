package com.lead.capture.feature.contact.controller

import com.lead.capture.ContactApi
import com.lead.capture.feature.contact.service.ContactService
import com.lead.capture.model.ContactResponse
import com.lead.capture.model.CreateNewContact

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/contact")
@CrossOrigin(origins = ["*"])
class ContactController(private val contactService: ContactService) : ContactApi {

    @GetMapping("/{contactEmail}")
    override fun getContactByEmail(@PathVariable contactEmail: String): ResponseEntity<ContactResponse> {
        return ResponseEntity.ok(contactService.getContactByEmail(contactEmail))
    }

    @PostMapping
    override fun saveContact(@Valid @RequestBody createNewContact: CreateNewContact): ResponseEntity<Void> {
       contactService.saveContact(createNewContact)
        return ResponseEntity.ok().build()
    }
}
