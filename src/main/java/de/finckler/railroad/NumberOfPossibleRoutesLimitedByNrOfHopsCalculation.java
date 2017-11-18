package de.finckler.railroad;

import java.util.List;

import lombok.Value;

import de.finckler.railroad.domain.City;
import de.finckler.railroad.domain.Route;

@Value
class NumberOfPossibleRoutesLimitedByNrOfHopsCalculation {

  private final RouteNetwork routeNetwork;

  /**
   * Calculates, how many routes exists between start and end city with the given hop count.
   * If start and endcity are equal, roundtrips are counted.
   * Routes which travel several times through the endcity are counted each
   */
  int calculateNumberOfPossibleRoutesLimitedByNrOfHops(final City startCity, final City endCity, final int nrOfHops,
                                                       final boolean hitNrOfHopsExactly) {
    return calculateNumberOfPossibleRoutesRecursive(startCity, endCity, nrOfHops, hitNrOfHopsExactly, true);
  }

  private int calculateNumberOfPossibleRoutesRecursive(final City startCity, final City endCity, final int nrOfHops,
                                                       final boolean hitNrOfHopsExactly, final boolean ignoreCityMatch) {
    final boolean routeToTargetCityFound = startCity.equals(endCity) && !ignoreCityMatch;
    if (nrOfHops <= 0) {
      return routeToTargetCityFound ? 1 : 0;
    }
    final List<Route> routesStartingHere = routeNetwork.getRoutesStartingAt(startCity);
    final int numberOfPossibleRoutes = routesStartingHere.stream()
      .mapToInt(route -> calculateNumberOfPossibleRoutesRecursive(route.getEnd(), endCity, nrOfHops - 1,
        hitNrOfHopsExactly, false))
      .sum();
    if (hitNrOfHopsExactly) {
      return numberOfPossibleRoutes;
    } else {
      return numberOfPossibleRoutes + (routeToTargetCityFound ? 1 : 0);
    }
  }
}
