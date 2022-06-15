package br.com.softpethouse.api.commom.validation;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements javax.ws.rs.ext.ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException ex) {
        return ResponseError
                .createFromValidation(ex.getConstraintViolations())
                .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
    }

}
