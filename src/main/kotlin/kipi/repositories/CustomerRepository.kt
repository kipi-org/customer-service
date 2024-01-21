package kipi.repositories

import kipi.dao.Customers
import kipi.dao.Customers.dateOfCreate
import kipi.dao.Customers.name
import kipi.dao.Customers.surname
import kipi.dao.Customers.userId
import kipi.dto.Customer
import kipi.dto.CustomerDraft
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime.now

class CustomerRepository {
    fun createCustomer(userId: Long, customerDraft: CustomerDraft) = transaction {
        Customers.insert {
            it[Customers.userId] = userId
            it[name] = customerDraft.name
            it[surname] = customerDraft.surname
            it[dateOfCreate] = now()
        }
    }

    fun findCustomer(userId: Long) = transaction {
        Customers.select {
            Customers.userId eq userId
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
        dateOfCreate = resultRow[dateOfCreate]
    )
}