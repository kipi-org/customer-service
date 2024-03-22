package kipi.repositories

import kipi.dao.Customers
import kipi.dao.Customers.dateOfCreate
import kipi.dao.Customers.email
import kipi.dao.Customers.name
import kipi.dao.Customers.surname
import kipi.dao.Customers.userId
import kipi.dto.Customer
import kipi.dto.CustomerDraft
import kipi.dto.CustomerUpdates
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime.now

class CustomerRepository {
    fun createCustomer(userId: Long, customerDraft: CustomerDraft) = transaction {
        Customers.insert {
            it[Customers.userId] = userId
            it[name] = customerDraft.name
            it[surname] = customerDraft.surname
            it[email] = customerDraft.email
            it[dateOfCreate] = now()
        }
    }

    fun updateCustomer(userId: Long, customerUpdates: CustomerUpdates) = transaction {
        Customers.update({ Customers.userId eq userId }) {
            customerUpdates.name?.let { name -> it[Customers.name] = name }
            customerUpdates.surname?.let { surname -> it[Customers.surname] = surname }
        }
    }

    fun findCustomer(userId: Long) = transaction {
        Customers.select {
            Customers.userId eq userId
        }.map { mapToCustomer(it) }.firstOrNull()
    }

    fun findCustomerByEmail(email: String) = transaction {
        Customers.select {
            Customers.email eq email
        }.map { mapToCustomer(it) }.firstOrNull()
    }

    fun deleteCustomer(userId: Long) = transaction {
        Customers.deleteWhere {
            Customers.userId eq userId
        }
    }

    private fun mapToCustomer(resultRow: ResultRow) = Customer(
        userId = resultRow[userId],
        name = resultRow[name],
        surname = resultRow[surname],
        email = resultRow[email],
        dateOfCreate = resultRow[dateOfCreate]
    )
}