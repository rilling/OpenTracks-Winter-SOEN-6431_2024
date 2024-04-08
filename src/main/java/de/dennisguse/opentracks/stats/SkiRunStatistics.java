package de.dennisguse.opentracks.stats;

import de.dennisguse.opentracks.data.models.Distance;
import de.dennisguse.opentracks.data.models.Speed;
import de.dennisguse.opentracks.data.models.TrackPoint;
import java.util.*;
import java.time.Duration;
public class SkiRunStatistics {

    private String name;
    private double average_speed;

    // Segments / Trackpoints of Run
    private List<TrackPoint> trackPoints;

    // Constructor
    public SkiRunStatistics(String name, List<TrackPoint> trackPoints) {

        this.name = name;
        this.trackPoints = trackPoints;
    }

    public String getName() {
        return name;
    }

    public void setTrackPoints(List<TrackPoint> trackPoints) {
        this.trackPoints = trackPoints;
    }

    //Getter and Setter for Average Speed
    public double getAverageSpeed() {
        return average_speed;
    }

    public void setAverageSpeed(double speed) {
        this.average_speed = speed;

    }

    // Determine the start and end points of the ski run based on trackpoints
    public TrackPoint getStartPoint() {
        if (trackPoints.isEmpty()) {
            return null;
        }
        return trackPoints.get(0);
    }

    public TrackPoint getEndPoint() {
        if (trackPoints.isEmpty()) {
            return null;
        }
        return trackPoints.get(trackPoints.size() - 1);
    }

    // Calculate the time of the ski run
    public Duration getDuration() {
        if (trackPoints.isEmpty()) {
            return Duration.ZERO;
        }
        TrackPoint startPoint = getStartPoint();
        TrackPoint endPoint = getEndPoint();
        return Duration.between(startPoint.getTime(), endPoint.getTime());
    }

    // Method to calculate the total distance covered during the ski run
    public double getTotalDistance() {
        double totalDistance = 0.0;
        for (int i = 1; i < trackPoints.size(); i++) {
            TrackPoint pPoint = trackPoints.get(i - 1);
            TrackPoint cPoint = trackPoints.get(i);
            Distance distance = pPoint.distanceToPrevious(cPoint);
            totalDistance += distance.toKM();
        }
        return totalDistance;
    }
    // Method to calculate the speed information average of a run based on track points
    public void calculateSpeedStatistics()
    {

        double totalSpeed = 0;
        ArrayList<Speed> speedList = new ArrayList<>();
        for (TrackPoint tp: this.trackPoints)
        {
            Speed speed = tp.getSpeed();
            speedList.add(speed);
            totalSpeed += speed.toMPS(); // convert speed to m/s
        }

        // get average speed for ski run
        double averageSpeed = totalSpeed / speedList.size();

        // Set ski run average speed
        this.average_speed = averageSpeed;
    }

    // Determine if the user was skiing
    public boolean isUserSking() {
        if (trackPoints.size() < 2) {
            return false; // Not enough data
        }

        // Thresholds to determine skiing activity
        double altitudeChangeThreshold = 10.0; // Meters
        double speedThreshold = 5.0; // Meters per second
        long timeThresholdInSeconds = 50; // Seconds

        // Get the first and last track points
        TrackPoint startPoint = getStartPoint();
        TrackPoint endPoint = getEndPoint();

        // Check if altitude change is significant
        double altitudeChange = Math.abs(startPoint.getAltitude().toM() - endPoint.getAltitude().toM());
        if (altitudeChange < altitudeChangeThreshold) {
            return false; // Altitude change not significant, likely not skiing
        }

        // Calculate total distance
        double totalDistance = getTotalDistance();

        // Calculate total time (in seconds)
        long totalTimeInSeconds = getDuration().getSeconds();

        // Calculate average speed
        double averageSpeed = totalDistance / totalTimeInSeconds;

        // Check if average speed is above the speed threshhold
        if (averageSpeed < speedThreshold) {
            // Average speed too slow, probably not skiing
            return false;
        }

        // Check if total time is above time threshold
        if (totalTimeInSeconds < timeThresholdInSeconds) {
            // Duration too short, probably not skiing
            return false;
        }
        // User is likely skiing
        return true;
    }

}