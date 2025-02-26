package kipi

import kipi.controllers.*
import kipi.repositories.CustomerRepository
import kipi.services.CustomerService

class Dependencies {
    val config = Config()
    private val customerRepository = CustomerRepository()
    private val customerService = CustomerService(customerRepository)
    val customerCreateController = CustomerCreateController(customerService)
    val customerUpdateController = CustomerUpdateController(customerService)
    val customerDeleteController = CustomerDeleteController(customerService)
    val customerFindController = CustomerFindController(customerService)
}