package kipi.services

import kipi.dto.Customer
import kipi.dto.CustomerDraft
import kipi.exceptions.CustomerAlreadyExistException
import kipi.exceptions.CustomerNotExistException
import kipi.repositories.CustomerRepository

class CustomerService(
    private val customerRepository: CustomerRepository
) {

    fun createCustomer(userId: Long, customerDraft: CustomerDraft) {
        if (customerRepository.findCustomer(userId) != null) throw CustomerAlreadyExistException("This customer already created")

        customerRepository.createCustomer(userId, customerDraft)
    }

    fun findCustomer(userId: Long): Customer {
        val customer = customerRepository.findCustomer(userId)
        customer ?: throw CustomerNotExistException("This customer not exist")

        return customer
    }

    fun findCustomerByEmail(email: String): Customer {
        val customer = customerRepository.findCustomerByEmail(email)
        customer ?: throw CustomerNotExistException("This customer not exist")

        return customer
    }


    fun deleteCustomer(userId: Long) {
        customerRepository.deleteCustomer(userId)
    }
}