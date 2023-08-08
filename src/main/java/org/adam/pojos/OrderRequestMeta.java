package org.adam.pojos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class OrderRequestMeta {
    Map<String, String> raw;

    public Map<String, String> getRaw() {
        return raw;
    }

    public void setRaw(Map<String, String> raw) {
        this.raw = raw;
    }

    public Integer getEndWaypointArrangement() {
        ArrayList<Integer> waypointArrangements = new ArrayList<>();
        for (Map.Entry<String, String> entry : raw.entrySet()) {
            if (entry.getKey().contains("waypoint")) {
                waypointArrangements.add(Integer.valueOf(entry.getKey().split("_")[1]));
            }
        }
        waypointArrangements.sort(Collections.reverseOrder());
        return waypointArrangements.get(0);
    }
}
