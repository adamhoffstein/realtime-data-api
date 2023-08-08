package org.adam.handlers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;

@Log
@Provider
public class IndexOutOfBoundsExceptionHandler implements ExceptionMapper<IndexOutOfBoundsException> {
    @Override
    public Response toResponse(IndexOutOfBoundsException e) {
        log.severe(e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
