package org.megras.api.rest.handlers

import io.javalin.http.Context
import org.megras.api.rest.GetRequestHandler
import org.megras.api.rest.RestErrorStatus
import org.megras.data.fs.FileSystemObjectStore
import org.megras.data.fs.StoredObjectId
import org.megras.data.mime.MimeType

class RawObjectRequestHandler(private val objectStore: FileSystemObjectStore) : GetRequestHandler {

    override fun get(ctx: Context) {

        val id = StoredObjectId.of(ctx.pathParam("objectId")) ?: throw RestErrorStatus(403, "invalid id")

        streamObject(id, objectStore, ctx)

    }

    companion object{
        fun streamObject(id: StoredObjectId, objectStore: FileSystemObjectStore, ctx: Context) {
            val result = objectStore.get(id) ?: throw RestErrorStatus.notFound
            ctx.header("Cache-Control", "max-age=31622400")
            val contentType = if (result.descriptor.mimeType == MimeType.TEXT) {
                "text/plain; charset=utf-8"
            } else {
                result.descriptor.mimeType.mimeString
            }
            ctx.writeSeekableStream(result.inputStream(), contentType)

        }
    }

}