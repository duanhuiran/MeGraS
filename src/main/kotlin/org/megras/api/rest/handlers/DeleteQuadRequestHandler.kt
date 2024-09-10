package org.megras.api.rest.handlers

import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.javalin.openapi.*
import org.megras.api.rest.PostRequestHandler
import org.megras.api.rest.RestErrorStatus
import org.megras.api.rest.data.ApiBasicQuery
import org.megras.api.rest.data.ApiQuad
import org.megras.api.rest.data.ApiQueryResult
import org.megras.data.graph.QuadValue
import org.megras.graphstore.MutableQuadSet

class DeleteQuadRequestHandler(private val quads: MutableQuadSet) : PostRequestHandler {

    @OpenApi(
        summary = "Deletes matching quads from the graph.",
        path = "/delete/quads",
        tags = ["Deletion"],
        operationId = OpenApiOperation.AUTO_GENERATE,
        methods = [HttpMethod.POST],
        requestBody = OpenApiRequestBody([OpenApiContent(ApiBasicQuery::class)]),
        responses = [
            OpenApiResponse("200", [OpenApiContent(ApiQueryResult::class)]),
            OpenApiResponse("400", [OpenApiContent(RestErrorStatus::class)]),
            OpenApiResponse("404", [OpenApiContent(RestErrorStatus::class)]),
        ]
    )
    override fun post(ctx: Context) {
        val query = try {
            ctx.bodyAsClass(ApiBasicQuery::class.java)
        } catch (e: BadRequestResponse) {
            throw RestErrorStatus(400, "invalid query")
        }

        val s = query.s?.mapNotNull { if (it != null) QuadValue.of(it) else null }
        val p = query.p?.mapNotNull { if (it != null) QuadValue.of(it) else null }
        val o = query.o?.mapNotNull { if (it != null) QuadValue.of(it) else null }

        val quadsToDelete = quads.filter(
            if (s?.isNotEmpty() == true) s else null,
            if (p?.isNotEmpty() == true) p else null,
            if (o?.isNotEmpty() == true) o else null
        )

        if (quadsToDelete.isEmpty()) {
            throw RestErrorStatus(404, "No matching quads found for deletion")
        }

        // Convert the quads to ApiQuad format
        val deletedQuads = quadsToDelete.map {
            ApiQuad(it)
        }

        quadsToDelete.forEach { quad -> quads.remove(quad) }


        ctx.json(ApiQueryResult(deletedQuads))
    }
}
