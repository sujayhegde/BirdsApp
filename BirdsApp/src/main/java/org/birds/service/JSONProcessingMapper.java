package org.birds.service;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import org.codehaus.jackson.JsonProcessingException;

/*
        JSON Processing mapper
 */

@Provider
public class JSONProcessingMapper implements ExceptionMapper<JsonProcessingException> {
    public Response toResponse(JsonProcessingException e) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}

