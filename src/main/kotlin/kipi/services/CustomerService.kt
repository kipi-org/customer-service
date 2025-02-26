package kipi.services

import kipi.dto.Customer
import kipi.dto.CustomerDraft
import kipi.dto.CustomerUpdates
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

    fun updateCustomer(userId: Long, customerUpdates: CustomerUpdates) {
        customerRepository.updateCustomer(userId, customerUpdates)
    }

    fun findCustomer(userId: Long): Customer {
        val customer = customerRepository.findCustomer(userId)
        customer ?: throw CustomerNotExistException("customer.user.not.exist")

        return customer
    }

    fun deleteCustomer(userId: Long) {
        customerRepository.deleteCustomer(userId)
    }
}