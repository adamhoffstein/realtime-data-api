package org.adam;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jboss.resteasy.reactive.RestResponse.Status.OK;

@QuarkusTest
public class DriverResourceTest {
    @Test
    void shouldGetValidDriverOrderStats() {
        given()
            .queryParam("id", 123)
            .queryParam("country", "sg")
            .when().get("/drivers/order_stats")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON);
    }

    @Test
    void shouldGetValidDriverDetails() {
        given()
            .queryParam("id", 123)
            .queryParam("country", "sg")
            .when().get("/drivers/location")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON);
    }
}
