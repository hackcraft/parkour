package com.hackclub.hackcraft.parkour.objects;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkourMap implements ConfigurationSerializable {

    private String id;
    private String name;
    private Location start;
    private Location end;
    private ArrayList<Location> checkpoints;

    public ParkourMap(String id, String name) {
        this.id = id;
        this.name = name;
        checkpoints = new ArrayList<Location>();
    }

    public ParkourMap(Map<String, Object> serializedParkourMap) {
        this.id = (String) serializedParkourMap.get("id");
        this.name = (String) serializedParkourMap.get("name");
        this.start = Location.deserialize((Map<String, Object>) serializedParkourMap.get("start"));
        this.end = Location.deserialize((Map<String, Object>) serializedParkourMap.get("end"));

        ArrayList<Map<String, Object>> mappedCheckpoints = (ArrayList<Map<String, Object>>) serializedParkourMap.get("checkpoints");
        this.checkpoints = new ArrayList<>();
        mappedCheckpoints.forEach((c) -> this.checkpoints.add(Location.deserialize(c)));
    }



    public Location getStart() {
        return start;
    }

    public Location getEnd() {
        return end;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }


    public boolean addCheckpoint(Location toAdd) {
        return checkpoints.add(toAdd);
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public void setEnd(Location end) {
        this.end = end;
    }

    public ArrayList<Location> getCheckpoints() {
        return checkpoints;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> serializer = new HashMap<>();


        // serialize basic params
        serializer.put("id", id);
        serializer.put("name", name);
        serializer.put("start", start.serialize());
        serializer.put("end", end.serialize());

        // serialize arraylist of checkpoints
        ArrayList<Map<String, Object>> checkpointsSerialized = new ArrayList<>();
        checkpoints.forEach((c) -> checkpointsSerialized.add(c.serialize()));


        // add the serialized list
        serializer.put("checkpoints", checkpointsSerialized);

        return serializer;
    }


}
