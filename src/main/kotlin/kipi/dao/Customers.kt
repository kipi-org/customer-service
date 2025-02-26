package kipi.dao

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object Customers : Table("customers") {
    val userId = long("userId")
    val name = varchar("name", 255)
    val surname = varchar("surname", 255)
    val dateOfCreate = datetime("dateOfCreate")
    override val primaryKey = PrimaryKey(userId)
}