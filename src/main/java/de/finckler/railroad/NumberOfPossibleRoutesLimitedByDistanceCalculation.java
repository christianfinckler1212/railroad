package de.finckler.railroad;

import java.util.List;

import lombok.Value;

import de.finckler.railroad.domain.City;
import de.finckler.railroad.domain.Route;

@Value
class NumberOfPossibleRoutesLimitedByDistanceCalculation {

  private final RouteNetwork routeNetwork;

  /**
   * Calculates, how many routes exists between start and end city with the given max distance.
   * If start and endcity are equal, roundtrips are counted.
   * Routes which travel several times through the endcity are counted each
   */
  int calculateNumberOfPossibleRoutesByDistance(final City startCity, final City endCity, final int maxDistance) {
    return calculateNumberOfPossibleRoutesRecursive(startCity, endCity, maxDistance, true);
  }

  private int calculateNumberOfPossibleRoutesRecursive(final City startCity, final City endCity, final int distanceLeft,
                                                       final boolean ignoreCityMatch) {
    final boolean routeToTargetCityFound = startCity.equals(endCity) && !ignoreCityMatch;
    System.out.println(startCity + "distance: " + distanceLeft + " routeToTargetCityFound: " + routeToTargetCityFound);
    if (distanceLeft < 0) {
      return 0;
    }
    final List<Route> routesStartingHere = routeNetwork.getRoutesStartingAt(startCity);
    final int numberOfPossibleRoutes = routesStartingHere.stream()
      .mapToInt(
        route -> calculateNumberOfPossibleRoutesRecursive(route.getEnd(), endCity, distanceLeft - route.getDistance(), false))
      .sum();
    return numberOfPossibleRoutes + (routeToTargetCityFound ? 1 : 0);
  }
}
