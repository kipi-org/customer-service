package kipi.controllers

import kipi.services.CustomerService


class CustomerFindByEmailController(
    private val customerService: CustomerService
) {
    fun handle(email: String) = customerService.findCustomerByEmail(email)
}