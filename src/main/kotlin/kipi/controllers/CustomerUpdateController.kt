package kipi.controllers

import kipi.dto.CustomerUpdates
import kipi.services.CustomerService

class CustomerUpdateController(
    private val customerService: CustomerService
) {
    fun handle(userId: Long, customerUpdates: CustomerUpdates) = customerService.updateCustomer(userId, customerUpdates)
}