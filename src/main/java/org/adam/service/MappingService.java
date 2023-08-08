package org.adam.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.adam.OrderRequestResource;
import org.adam.exceptions.ObjectNotFoundException;
import com.gogox.pojos.*;
import io.quarkus.arc.Arc;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.adam.pojos.*;
import org.jboss.logging.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
public class MappingService {

    @Inject
    RedisService redisService;

    private static final Logger LOG = Logger.getLogger(OrderRequestResource.class);

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    private ObjectMapper getObjectMapper() {
        return Arc.container().instance(ObjectMapper.class).get();
    }


    public Map<String, Boolean> getFlagsExist(List<String> flags, String countryCode) {
        HashMap<String, Boolean> flagMap = new HashMap<>();
        for (String flag: flags
             ) {
            String key = String.format("%s_db:flags:%s", countryCode, flag);
            flagMap.put(key, redisService.exists(key));
        }
        return flagMap;
    }

    public OrderRequestMeta getOrderRequestMeta(Integer orderRequestId, String countryCode) throws ObjectNotFoundException {
        String key = String.format("%s_db:order_request_meta:%d", countryCode, orderRequestId);
        Map<String, String> rawOrderRequestMeta = redisService.hgetall(key);
        if (rawOrderRequestMeta.isEmpty()) {
            LOG.error(String.format("Unable to find any entries under key: %s", key));
            throw new ObjectNotFoundException(key);
        }
        var newOrderRequestMeta = new OrderRequestMeta();
        newOrderRequestMeta.setRaw(rawOrderRequestMeta);
        return newOrderRequestMeta;
    }

    public DriverDetail getDriverDetails(Integer driverId, String countryCode) throws JsonProcessingException {
        Date currentDate = new Date();
        return getObjectMapper()
                .readValue(
                        redisService.zrange(
                                String.format(
                                        "%s_db:driver_details:%d:%s",
                                        countryCode,
                                        driverId,
                                        dateFormat.format(currentDate)
                                )
                        ),
                        DriverDetail.class
                );
    }

    public OrderRequest getOrderRequest(Integer orderRequestId, String countryCode) throws JsonProcessingException {
        return getObjectMapper()
                .readValue(
                        redisService.zrange(String.format("%s_db:order_requests:%d", countryCode, orderRequestId)),
                        OrderRequest.class
                );

    }

    public OrderRequestDetail getOrderRequestDetail(Integer orderRequestId, String countryCode) throws JsonProcessingException {
        return getObjectMapper()
                .readValue(
                        redisService.zrange(String.format("%s_db:order_request_details:%d", countryCode, orderRequestId)),
                        OrderRequestDetail.class
                );

    }

    public Waypoint getWaypoint(Integer orderRequestId, Integer arrangement, String countryCode) throws JsonProcessingException {
        return getObjectMapper()
                .readValue(
                        redisService.zrange(String.format("%s_db:waypoints:%d:%d", countryCode, orderRequestId, arrangement)),
                        Waypoint.class
                );
    }

    public DriverOrderStats getDriverOrderStats(Integer driverId, String countryCode) throws JsonProcessingException {
        Map<String, String> rawDriverStats = redisService.hgetall(
                String.format("%s_db:metrics:drivers:order_stats:%d", countryCode, driverId)
        );

        DriverOrderStats driverOrderStats;
        ArrayList<OrderRequestSmall> orderRequests = new ArrayList<>();

        for (Map.Entry<String, String> entry : rawDriverStats.entrySet()) {
            OrderRequestSmall readOrderRequest = getObjectMapper()
                    .readValue(entry.getValue(), OrderRequestSmall.class);

            if (readOrderRequest.getOrderRequestEventMeta().getPickupTime() == null) {
                OrderRequest lookupOrderRequest = getOrderRequest(readOrderRequest.getId(), countryCode);
                OrderRequestEventMeta newOrderRequestEventMeta = new OrderRequestEventMeta();
                newOrderRequestEventMeta.setPickupTime(lookupOrderRequest.getPickupTime().toInstant());
                readOrderRequest.setOrderRequestEventMeta(newOrderRequestEventMeta);
            }

            orderRequests.add(readOrderRequest);
        }

        driverOrderStats = new DriverOrderStats();
        driverOrderStats.setDriverId(driverId);
        driverOrderStats.setInteractedOrders(orderRequests);

        return driverOrderStats;

    }

}
