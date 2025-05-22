package com.lead.capture.feature.contact.service

import com.lead.capture.feature.contact.model.Contact
import com.lead.capture.model.ContactResponse
import com.lead.capture.model.CreateNewContact
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
interface ContactMapper {

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "createdAt", expression = "java(OffsetDateTime.now())")
    fun toInternal(contact: CreateNewContact) : Contact

    fun toResponse(contact: Contact) : ContactResponse


}
