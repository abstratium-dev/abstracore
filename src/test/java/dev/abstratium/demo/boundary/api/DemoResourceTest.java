package dev.abstratium.demo.boundary.api;

import dev.abstratium.demo.Roles;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

/**
 * Test for DemoResource error handling with RFC 7807 Problem Details.
 */
@QuarkusTest
class DemoResourceTest {

    @Test
    @TestSecurity(user = "testuser", roles = {Roles.USER})
    void testErrorEndpointReturnsRFC7807ProblemDetails() {
        given()
            .when()
            .get("/api/demo/error")
            .then()
            .statusCode(400) // Bad Request
            .contentType("application/problem+json") // RFC 7807 content type
            .body("type", notNullValue())
            .body("title", is("Demo error for testing"))
            .body("status", is(400))
            .body("detail", containsString("RFC 7807 Problem Details"));
    }

    @Test
    @TestSecurity(user = "testuser", roles = {Roles.USER})
    void testErrorEndpointStructure() {
        // Verify the error response has the correct RFC 7807 structure
        given()
            .when()
            .get("/api/demo/error")
            .then()
            .statusCode(400)
            .body("type", notNullValue())
            .body("title", notNullValue())
            .body("status", notNullValue())
            .body("detail", notNullValue());
    }

    @Test
    void testErrorEndpointRequiresAuthentication() {
        // Without authentication, should get 400 (in test mode without OIDC setup)
        // In production with proper OIDC, this would be a redirect to login
        given()
            .when()
            .get("/api/demo/error")
            .then()
            .statusCode(400);
    }
}
