package dev.abstratium.core.boundary;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * RFC 7807 Problem Details response structure.
 * 
 * This class is kept for backward compatibility with OAuth 2.0 error responses
 * and custom error handling. For new code, use FunctionalException which extends
 * HttpProblem and automatically generates RFC 7807 compliant responses.
 * 
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc7807">RFC 7807</a>
 */
@RegisterForReflection
@Schema(description = "RFC 7807 Problem Details")
public class ErrorResponse {
    
    @Schema(description = "A URI reference that identifies the problem type", examples = {"https://example.com/probs/out-of-credit"})
    public String type;
    
    @Schema(description = "A short, human-readable summary of the problem type", examples = {"You do not have enough credit."})
    public String title;
    
    @Schema(description = "The HTTP status code", examples = {"403"})
    public Integer status;
    
    @Schema(description = "A human-readable explanation specific to this occurrence", examples = "Your current balance is 30, but that costs 50.")
    public String detail;
    
    @Schema(description = "A URI reference that identifies the specific occurrence", examples = {"https://example.com/account/12345/msgs/abc"})
    public String instance;
    
    /**
     * Default constructor for cases where fields are set after construction.
     */
    public ErrorResponse() {
    }
    
    /**
     * Create error response with title and detail.
     */
    public ErrorResponse(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }
    
    /**
     * Create error response with status, title, and detail.
     */
    public ErrorResponse(Integer status, String title, String detail) {
        this.status = status;
        this.title = title;
        this.detail = detail;
    }
    
    /**
     * Create full RFC 7807 error response.
     */
    public ErrorResponse(String type, String title, Integer status, String detail, String instance) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.instance = instance;
    }
}
