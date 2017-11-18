package de.finckler.railroad;

import java.util.List;
import java.util.Optional;

import lombok.Value;

import de.finckler.railroad.domain.City;
import de.finckler.railroad.domain.Route;

@Value
class TripDistanceCalculation {

  private final RouteNetwork routeNetwork;

  /**
   * Calculates the sum of the distance between the given list of cities.
   * If the cities are not connected, Optional.empty is returned
   */
  Optional<Integer> getTripDistance(final List<City> cities) {
    if (cities.size() == 0) {
      throw new IllegalArgumentException("Call with zero cities is invalid");
    }
    if (cities.size() == 1) {
      return Optional.of(0);
    }
    final City firstCity = cities.get(0);
    final City secondCity = cities.get(1);

    final Optional<Route> routeBetween = routeNetwork.getRouteBetween(firstCity, secondCity);
    if (routeBetween.isPresent()) {
      return getTripDistance(cities.subList(1, cities.size())).map(distance -> distance + routeBetween.get().getDistance());
    } else {
      return Optional.empty();
    }
  }

}
