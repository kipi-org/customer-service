package kipi

import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kipi.dto.CustomerDraft
import kipi.dto.CustomerUpdates

fun Application.routes(deps: Dependencies) = with(deps) {
    routing {
        get("/health") {
            call.respond(OK)
        }

        route("/customer/{userId}") {
            post<CustomerDraft> {
                customerCreateController.handle(call.userId, it)
                call.respond(OK)
            }

            put<CustomerUpdates> {
                customerUpdateController.handle(call.userId, it)
                call.respond(OK)
            }

            get {
                call.respond(OK, customerFindController.handle(call.userId))
            }

            delete {
                customerDeleteController.handle(call.userId)
                call.respond(OK)
            }
        }
    }
}

private val ApplicationCall.userId: Long
    get() = this.parameters.getOrFail("userId").toLong()
