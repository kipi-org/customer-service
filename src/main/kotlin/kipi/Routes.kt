package kipi

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kipi.dto.CustomerDraft

fun Application.routes(deps: Dependencies) = with(deps) {
    routing {
        get("/health") {
            call.respond(HttpStatusCode.OK)
        }

        route("/customer/{userId}") {
            post<CustomerDraft> {
                customerCreateController.handle(call.userId, it)
                call.respond(HttpStatusCode.OK)
            }

            get {
                call.respond(HttpStatusCode.OK, customerFindController.handle(call.userId))
            }

            delete {
                customerDeleteController.handle(call.userId)
                call.respond(HttpStatusCode.OK)
            }
        }

        route("/customer/email") {
            get {
                call.respond(HttpStatusCode.OK, customerFindByEmailController.handle(call.email))
            }
        }
    }
}

private val ApplicationCall.userId: Long
    get() = this.parameters.getOrFail("userId").toLong()

private val ApplicationCall.email: String
    get() = this.parameters.getOrFail("email")
