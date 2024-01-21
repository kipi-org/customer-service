package kipi.controllers

import kipi.services.CustomerService


class CustomerFindController(
    private val customerService: CustomerService
) {
    fun handle(userId: Long) = customerService.findCustomer(userId)
}