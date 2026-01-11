package dev.abstratium.core.boundary;

import io.quarkiverse.resteasy.problem.HttpProblem;
import jakarta.ws.rs.core.Response;
import java.net.URI;

/**
 * Base exception for functional/business logic errors that should be returned as RFC 7807 Problem Details.
 * 
 * This exception extends HttpProblem to automatically generate application/problem+json responses
 * with proper HTTP status codes and structured error information.
 * 
 * Each exception should use an ErrorCode to ensure unique, trackable errors with wiki documentation.
 */
public class FunctionalException extends HttpProblem {
    
    /**
     * Create a FunctionalException with an error code.
     * The error code's URI will be used as the 'type' field in the problem+json response.
     * 
     * @param status HTTP status code
     * @param errorCode Unique error code that maps to wiki documentation
     * @param detail Detailed description of this specific error instance
     */
    public FunctionalException(Response.Status status, ErrorCode errorCode, String detail) {
        super(builder()
            .withStatus(status)
            .withTitle(errorCode.getDescription())
            .withDetail(detail)
            .withType(errorCode.getTypeUri()));
    }
    
    /**
     * Create a FunctionalException with a custom title (legacy constructor).
     * Prefer using the ErrorCode constructor for better error tracking.
     */
    public FunctionalException(Response.Status status, String title, String detail) {
        super(builder()
            .withStatus(status)
            .withTitle(title)
            .withDetail(detail));
    }
    
    /**
     * Create a FunctionalException with a custom type URI (legacy constructor).
     * Prefer using the ErrorCode constructor for better error tracking.
     */
    public FunctionalException(Response.Status status, String title, String detail, URI type) {
        super(builder()
            .withStatus(status)
            .withTitle(title)
            .withDetail(detail)
            .withType(type));
    }
}
