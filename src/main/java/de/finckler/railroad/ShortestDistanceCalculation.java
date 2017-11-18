package de.finckler.railroad;

import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

import lombok.Value;

import de.finckler.railroad.domain.City;

@Value
class ShortestDistanceCalculation {

  private final RouteNetwork routeNetwork;

  /**
   * Calculates the shortest distance betwenn start and end city. If start and endcity are equal, roundtrips with at least two
   * hops are searched.
   */
  Optional<Integer> calculateShortestDistance(final City startCity, final City endCity) {
    return calculateShortestDistanceForAllOutgoingRoutes(startCity, endCity, new HashSet<>());
  }

  /**
   * Calculates the shortest distance between start and end city.
   * If both cities are the same, the shortest path is found and 0 is returned.
   * If not, we call this method recursively with all connected cities, if they are not already traveled.
   */
  private Optional<Integer> calculateShortestDistanceRecursive(final City startCity, final City endCity,
                                                               final Set<City> traveledCities) {
    if (startCity.equals(endCity)) {
      return Optional.of(0);
    } else {
      final Set<City> traveledCitiesPlusCurrentCity = new HashSet<>(traveledCities);
      traveledCitiesPlusCurrentCity.add(startCity);

      return calculateShortestDistanceForAllOutgoingRoutes(startCity, endCity,
        traveledCitiesPlusCurrentCity);
    }
  }

  private Optional<Integer> calculateShortestDistanceForAllOutgoingRoutes(final City startCity, final City endCity,
                                                                          final Set<City> traveledCities) {
    return mapToOptional(routeNetwork.getRoutesStartingAt(startCity).stream()
      .filter(o -> !traveledCities.contains(o.getEnd()))
      .map(route -> calculateShortestDistanceRecursive(route.getEnd(), endCity, traveledCities)
        .map(distance -> distance + route.getDistance()))
      .filter(Optional::isPresent)
      .mapToInt(Optional::get)
      .min());
  }

  // With java 9 OptionalInt.stream exists. Mapping is then possible without this ugly if
  private static Optional<Integer> mapToOptional(final OptionalInt optInt) {
    if (optInt.isPresent()) {
      return Optional.of(optInt.getAsInt());
    } else {
      return Optional.empty();
    }
  }
}
