package de.finckler.railroad;

import static java.util.Arrays.asList;

import static de.finckler.railroad.TestCityProvider.CITY_A;
import static de.finckler.railroad.TestCityProvider.CITY_B;
import static de.finckler.railroad.TestCityProvider.CITY_C;
import static de.finckler.railroad.TestCityProvider.CITY_D;
import static de.finckler.railroad.TestCityProvider.CITY_E;

import java.util.List;

import lombok.experimental.UtilityClass;

import de.finckler.railroad.domain.Route;

@UtilityClass
class TestRouteNetworkProvider {

  static RouteNetwork officialRouteNetwork() {

    final List<Route> routes = asList(
      new Route(CITY_A, CITY_B, 5),
      new Route(CITY_B, CITY_C, 4),
      new Route(CITY_C, CITY_D, 8),
      new Route(CITY_D, CITY_C, 8),
      new Route(CITY_D, CITY_E, 6),
      new Route(CITY_A, CITY_D, 5),
      new Route(CITY_C, CITY_E, 2),
      new Route(CITY_E, CITY_B, 3),
      new Route(CITY_A, CITY_E, 7));

    return new RouteNetwork(routes);
  }

  static RouteNetwork bidirectionalTwoCityRouteNetwork() {

    final List<Route> routes = asList(
      new Route(CITY_A, CITY_B, 5),
      new Route(CITY_B, CITY_A, 5));

    return new RouteNetwork(routes);
  }

  static RouteNetwork notConnectedCityRouteNetwork() {

    final List<Route> routes = asList(
      new Route(CITY_A, CITY_B, 5),
      new Route(CITY_C, CITY_D, 5));

    return new RouteNetwork(routes);
  }
}
