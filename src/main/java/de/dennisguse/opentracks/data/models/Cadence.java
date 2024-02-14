package de.dennisguse.opentracks.data.models;

import java.time.Duration;

public record Cadence(float valueRPM) {

    public static Cadence of(float value, Duration duration) {
        if (duration.isZero()) {
            return zero();
        }

        return new Cadence(value / (duration.toMillis() / (float) Duration.ofMinutes(1).toMillis()));
    }

    public static Cadence of(float valueRPM) {
        return new Cadence(valueRPM);
    }

    public static Cadence zero() {
        return of(0.0f);
    }

    public float getRPM() {
        return valueRPM;
    }
}
