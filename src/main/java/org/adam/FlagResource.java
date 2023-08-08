package org.adam;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.adam.service.MappingService;
import org.adam.validators.CheckMultipleStringChoice;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Map;

@Path("/flags")
public class FlagResource {

    @Inject
    MappingService mappingService;

    @GET
    @Path("/exists")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Boolean> getDriverOrderStats(@QueryParam("flags") @NotNull List<String> flags, @QueryParam("country") @CheckMultipleStringChoice({"hk", "vn", "sg"}) @NotNull String countryCode) throws JsonProcessingException {
        return mappingService.getFlagsExist(flags, countryCode);
    }

}
