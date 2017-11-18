package de.finckler.railroad;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import de.finckler.railroad.domain.City;
import de.finckler.railroad.domain.Route;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
class RouteNetwork {

  private static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";
  private List<Route> routes;
  private Map<City, List<Route>> routesByStart;
  private Set<City> cities;
  private TripDistanceCalculation tripDistanceCalculation;
  private NumberOfPossibleRoutesLimitedByNrOfHopsCalculation numberOfPossibleRoutesLimitedByNrOfHopsCalculation;
  private NumberOfPossibleRoutesLimitedByDistanceCalculation numberOfPossibleRoutesLimitedByDistanceCalculation;
  private ShortestDistanceCalculation shortestDistanceCalculation;

  RouteNetwork(final List<Route> routes) {
    this.routes = routes;
    routesByStart = routes.stream().collect(groupingBy(Route::getStart));
    cities = new HashSet<>();
    routes.forEach(route -> {
      cities.add(route.getStart());
      cities.add(route.getEnd());
    });

    tripDistanceCalculation = new TripDistanceCalculation(this);
    numberOfPossibleRoutesLimitedByNrOfHopsCalculation = new NumberOfPossibleRoutesLimitedByNrOfHopsCalculation(this);
    numberOfPossibleRoutesLimitedByDistanceCalculation = new NumberOfPossibleRoutesLimitedByDistanceCalculation(this);
    shortestDistanceCalculation = new ShortestDistanceCalculation(this);
  }

  Optional<Route> getRouteBetween(final City startCity, final City endCity) {
    return routesByStart.get(startCity).stream().filter(route -> route.getEnd().equals(endCity)).findFirst();
  }

  List<Route> getRoutesStartingAt(final City startCity) {
    final List<Route> routesStartingHere = routesByStart.get(startCity);
    if (routesStartingHere == null) {
      return emptyList();
    } else {
      return routesStartingHere;
    }
  }

  String getTripDistance(final List<City> trip) {
    return tripDistanceCalculation.getTripDistance(trip)
      .map(Object::toString)
      .orElse(NO_SUCH_ROUTE);
  }

  int calculateNumberOfPossibleRoutesLimitedByNrHops(final City startCity, final City endCity, final int nrOfHops,
                                                     final boolean hitNrOfHopsExactly) {
    return numberOfPossibleRoutesLimitedByNrOfHopsCalculation.calculateNumberOfPossibleRoutesLimitedByNrOfHops(startCity, endCity,
      nrOfHops, hitNrOfHopsExactly);
  }

  int calculateNumberOfPossibleRoutesLimitedByDistance(final City startCity, final City endCity, final int maxDistance) {
    return numberOfPossibleRoutesLimitedByDistanceCalculation.calculateNumberOfPossibleRoutesByDistance(startCity, endCity,
      maxDistance);
  }

  String calculateShortestRoute(final City startCity, final City endCity) {
    return shortestDistanceCalculation.calculateShortestDistance(startCity, endCity)
      .map(Object::toString)
      .orElse(NO_SUCH_ROUTE);
  }
}
