package kipi.controllers

import kipi.dto.CustomerDraft
import kipi.services.CustomerService

class CustomerCreateController(
    private val customerService: CustomerService
) {
    fun handle(userId: Long, customerDraft: CustomerDraft) = customerService.createCustomer(userId, customerDraft)
}