package de.dennisguse.opentracks.data.models;

import java.util.ArrayList;
import java.util.List;

public class Run {

    private final String sessionId;
    private long startTime;
    private long endTime;
    private double maxSpeed;

    private double distance; // Consider adding other relevant properties (e.g., elevation gain/loss)
    private final List<TrackPoint> trackPoints;

    public Run(String sessionId) {
        this.sessionId = sessionId;
        this.trackPoints = new ArrayList<>();
    }

    // Getters
    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getDistance() {
        return distance;
    }

    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

    // Setters
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void addTrackPoint(TrackPoint trackPoint) {
        this.trackPoints.add(trackPoint);
    }



}
