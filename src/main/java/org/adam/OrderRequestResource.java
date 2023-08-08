package org.adam;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.adam.pojos.*;
import org.adam.service.MappingService;
import org.adam.validators.CheckMultipleStringChoice;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/orders")
public class OrderRequestResource {

    @Inject
    MappingService mappingService;


    @GET
    @Path("/order_requests")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderRequest getOrderRequest(@QueryParam("id") @NotNull Integer orderRequestId, @QueryParam("country") @CheckMultipleStringChoice({"hk", "vn", "sg"}) @NotNull String countryCode) throws JsonProcessingException {
        return mappingService.getOrderRequest(orderRequestId, countryCode);
    }

    @GET
    @Path("/waypoints")
    @Produces(MediaType.APPLICATION_JSON)
    public Waypoint getWaypoint(@QueryParam("order_request_id") @NotNull Integer orderRequestId, @QueryParam("arrangement") @NotNull Integer arrangement, @QueryParam("country") @CheckMultipleStringChoice({"hk", "vn", "sg"}) @NotNull String countryCode) throws JsonProcessingException {
        return mappingService.getWaypoint(orderRequestId, arrangement, countryCode);
    }

    @GET
    @Path("/with_details")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderRequestWithDetails getOrderRequestWithDetails(
            @QueryParam("id") @NotNull Integer orderRequestId,
            @QueryParam("country") @CheckMultipleStringChoice({"hk", "vn", "sg"}) @NotNull String countryCode,
            @QueryParam("order_request_details") @DefaultValue("false") Boolean needOrderRequestDetails,
            @QueryParam("end_waypoint") @DefaultValue("false") Boolean needEndWaypoint
    ) throws JsonProcessingException {
        OrderRequest orderRequest = mappingService.getOrderRequest(orderRequestId, countryCode);
        Waypoint startWaypoint = mappingService.getWaypoint(orderRequestId, 0, countryCode);
        OrderRequestWithDetails orderRequestWithDetails = OrderRequestWithDetails.of(orderRequest);
        OrderRequestMeta orderRequestMeta = mappingService.getOrderRequestMeta(orderRequestId, countryCode);
        orderRequestWithDetails.setStartWaypoint(startWaypoint);
        if (needOrderRequestDetails) {
            OrderRequestDetail orderRequestDetail = mappingService.getOrderRequestDetail(orderRequestId, countryCode);
            orderRequestWithDetails.setOrderRequestDetail(orderRequestDetail);
        }
        if (needEndWaypoint) {
            Waypoint endWaypoint = mappingService
                    .getWaypoint(
                            orderRequestId,
                            orderRequestMeta.getEndWaypointArrangement(),
                            countryCode
                    );
            orderRequestWithDetails.setEndWaypoint(endWaypoint);
        }
        return orderRequestWithDetails;
    }
}
