package org.adam;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.adam.pojos.DriverDetail;
import org.adam.pojos.DriverOrderStats;
import org.adam.service.MappingService;
import org.adam.validators.CheckMultipleStringChoice;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;


@Path("/drivers")
public class DriverResource {

    @Inject
    MappingService mappingService;

    @GET
    @Path("/order_stats")
    @Produces(MediaType.APPLICATION_JSON)
    public DriverOrderStats getDriverOrderStats(@QueryParam("id") @NotNull Integer driverId, @QueryParam("country") @CheckMultipleStringChoice({"hk", "vn", "sg"}) @NotNull String countryCode) throws JsonProcessingException {
        return mappingService.getDriverOrderStats(driverId, countryCode);
    }

    @GET
    @Path("/location")
    @Produces(MediaType.APPLICATION_JSON)
    public DriverDetail getDriverDetails(@QueryParam("id") @NotNull Integer driverId, @QueryParam("country") @CheckMultipleStringChoice({"hk", "vn", "sg"}) @NotNull String countryCode) throws JsonProcessingException {
        return mappingService.getDriverDetails(driverId, countryCode);
    }
}
