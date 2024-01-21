package kipi

import kipi.controllers.CustomerCreateController
import kipi.controllers.CustomerDeleteController
import kipi.controllers.CustomerFindController
import kipi.repositories.CustomerRepository
import kipi.services.CustomerService

class Dependencies {
    private val customerRepository = CustomerRepository()
    private val customerService = CustomerService(customerRepository)
    val customerCreateController = CustomerCreateController(customerService)
    val customerDeleteController = CustomerDeleteController(customerService)
    val customerFindController = CustomerFindController(customerService)
}