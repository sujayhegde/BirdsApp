package org.birds.service;

import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import java.io.EOFException;

/*
        Malformed JSON Exception MApper
 */
@Provider
public class MalformedJSONMapper implements ExceptionMapper<EOFException> {
    public Response toResponse(EOFException e) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
