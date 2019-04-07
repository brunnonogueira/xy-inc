package com.nogueira4j.xyinc.domain;

import java.util.function.Predicate;

public class PoiPredicate {

    private static final int DISTANCE_MAX = 10;

    public static Predicate<Double> isDistanceLessThanMax() {

        return  distance -> distance <= DISTANCE_MAX;
    }

}
