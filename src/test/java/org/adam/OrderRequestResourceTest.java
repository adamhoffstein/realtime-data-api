package org.adam;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jboss.resteasy.reactive.RestResponse.Status.NOT_FOUND;
import static org.jboss.resteasy.reactive.RestResponse.Status.OK;

@QuarkusTest
public class OrderRequestResourceTest {
    @Test
    void shouldGetValidOrderRequest() {
        given()
            .queryParam("id", 1234)
            .queryParam("country", "sg")
            .when().get("/orders/order_requests")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON);
    }

    @Test
    void shouldGetValidWaypoint() {
        given()
            .queryParam("order_request_id", 1234)
            .queryParam("country", "sg")
            .queryParam("arrangement", 0)
            .when().get("/orders/waypoints")
            .then()
            .statusCode(OK.getStatusCode())
            .contentType(APPLICATION_JSON);
    }

    @Test
    void checkMissingOrderRequest() {
        given()
            .queryParam("id", 5678)
            .queryParam("country", "sg")
            .when().get("/orders/order_requests")
            .then()
            .statusCode(NOT_FOUND.getStatusCode())
            .contentType(APPLICATION_JSON);
    }
}
