package kipi.controllers

import kipi.services.CustomerService

class CustomerDeleteController(
    private val customerService: CustomerService
) {
    fun handle(userId: Long) = customerService.deleteCustomer(userId)
}