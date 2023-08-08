package org.adam.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;

@Log
@Provider
public class JsonProcessingExceptionHandler implements ExceptionMapper<JsonProcessingException> {
    @Override
    public Response toResponse(JsonProcessingException e) {
        log.severe(e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
