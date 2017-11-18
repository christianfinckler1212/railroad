package de.finckler.railroad;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;

import static org.junit.Assert.assertEquals;

import static de.finckler.railroad.TestCityProvider.CITY_A;
import static de.finckler.railroad.TestCityProvider.CITY_B;
import static de.finckler.railroad.TestCityProvider.CITY_C;
import static de.finckler.railroad.TestCityProvider.CITY_D;
import static de.finckler.railroad.TestCityProvider.CITY_E;
import static de.finckler.railroad.TestRouteNetworkProvider.officialRouteNetwork;

import org.junit.Test;

public class RouteNetworkTest {

  @Test
  public void question1Distance() throws Exception {
    final RouteNetwork routeNetwork = officialRouteNetwork();

    final String shortestDistance = routeNetwork.getTripDistance(asList(CITY_A, CITY_B, CITY_C));

    assertEquals(valueOf(9), shortestDistance);
  }

  @Test
  public void question2Distance() throws Exception {
    final RouteNetwork routeNetwork = officialRouteNetwork();

    final String shortestDistance = routeNetwork.getTripDistance(asList(CITY_A, CITY_D));

    assertEquals(valueOf(5), shortestDistance);
  }

  @Test
  public void question3Distance() throws Exception {
    final RouteNetwork routeNetwork = officialRouteNetwork();

    final String shortestDistance = routeNetwork.getTripDistance(asList(CITY_A, CITY_D, CITY_C));

    assertEquals(valueOf(13), shortestDistance);
  }

  @Test
  public void question4Distance() throws Exception {
    final RouteNetwork routeNetwork = officialRouteNetwork();

    final String shortestDistance = routeNetwork.getTripDistance(asList(CITY_A, CITY_E, CITY_B, CITY_C, CITY_D));

    assertEquals(valueOf(22), shortestDistance);
  }

  @Test
  public void question5Distance() throws Exception {
    final RouteNetwork routeNetwork = officialRouteNetwork();

    final String shortestDistance = routeNetwork.getTripDistance(asList(CITY_A, CITY_E, CITY_D));

    assertEquals("NO SUCH ROUTE", shortestDistance);
  }

  @Test
  public void question6NrOfTripsWithHops() throws Exception {
    final RouteNetwork routeNetwork = officialRouteNetwork();

    final int shortestDistance = routeNetwork.calculateNumberOfPossibleRoutesLimitedByNrHops(CITY_C, CITY_C, 3, false);

    assertEquals(2, shortestDistance);
  }

  @Test
  public void question7NrOfTripsWithHops() throws Exception {
    final RouteNetwork routeNetwork = officialRouteNetwork();

    final int shortestDistance = routeNetwork.calculateNumberOfPossibleRoutesLimitedByNrHops(CITY_A, CITY_C, 4, true);

    assertEquals(3, shortestDistance);
  }

  @Test
  public void question8ShortestRoute() throws Exception {
    final RouteNetwork routeNetwork = officialRouteNetwork();

    final String shortestDistance = routeNetwork.calculateShortestRoute(CITY_A, CITY_C);

    assertEquals(valueOf(9), shortestDistance);
  }

  @Test
  public void question9ShortestRoute() throws Exception {
    final RouteNetwork routeNetwork = officialRouteNetwork();

    final String shortestDistance = routeNetwork.calculateShortestRoute(CITY_B, CITY_B);

    assertEquals(valueOf(9), shortestDistance);
  }

  @Test
  public void question10NrOfTripsWithDistance() throws Exception {
    final RouteNetwork routeNetwork = officialRouteNetwork();

    final int shortestDistance = routeNetwork.calculateNumberOfPossibleRoutesLimitedByDistance(CITY_C, CITY_C, 29);

    assertEquals(7, shortestDistance);
  }
}
