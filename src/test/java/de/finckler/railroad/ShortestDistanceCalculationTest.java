package de.finckler.railroad;

import static org.junit.Assert.assertEquals;

import static de.finckler.railroad.TestCityProvider.CITY_A;
import static de.finckler.railroad.TestCityProvider.CITY_B;
import static de.finckler.railroad.TestCityProvider.CITY_C;
import static de.finckler.railroad.TestCityProvider.CITY_D;
import static de.finckler.railroad.TestRouteNetworkProvider.bidirectionalTwoCityRouteNetwork;
import static de.finckler.railroad.TestRouteNetworkProvider.officialRouteNetwork;
import static de.finckler.railroad.TestRouteNetworkProvider.notConnectedCityRouteNetwork;

import java.util.Optional;

import org.junit.Test;

public class ShortestDistanceCalculationTest {

  @Test
  public void expectToCalculateShortestDistanceWithSameStartAndEnd() {
    final ShortestDistanceCalculation shortestDistanceCalculation =
      new ShortestDistanceCalculation(bidirectionalTwoCityRouteNetwork());

    final Optional<Integer> shortestDistance = shortestDistanceCalculation.calculateShortestDistance(CITY_A, CITY_A);

    assertEquals(Optional.of(10), shortestDistance);
  }

  @Test
  public void expectToCalculateShortestDistanceWithOneHop() {
    final ShortestDistanceCalculation shortestDistanceCalculation = new ShortestDistanceCalculation(
      bidirectionalTwoCityRouteNetwork());

    final Optional<Integer> shortestDistance = shortestDistanceCalculation.calculateShortestDistance(CITY_A, CITY_B);

    assertEquals(Optional.of(5), shortestDistance);
  }

  @Test
  public void expectNotToCalculateShortestDistanceIfNoRouteExists() {
    final ShortestDistanceCalculation shortestDistanceCalculation =
      new ShortestDistanceCalculation(notConnectedCityRouteNetwork());

    final Optional<Integer> shortestDistance = shortestDistanceCalculation.calculateShortestDistance(CITY_A, CITY_D);

    assertEquals(Optional.empty(), shortestDistance);
  }

  @Test
  public void expectToCalculateShortestDistanceWithMultipleHops() {
    final ShortestDistanceCalculation shortestDistanceCalculation = new ShortestDistanceCalculation(officialRouteNetwork());

    final Optional<Integer> shortestDistance = shortestDistanceCalculation.calculateShortestDistance(CITY_A, CITY_C);

    assertEquals(Optional.of(9), shortestDistance);
  }

  @Test
  public void expectToCalculateShortestDistanceWithARoundtrip() {
    final ShortestDistanceCalculation shortestDistanceCalculation = new ShortestDistanceCalculation(officialRouteNetwork());

    final Optional<Integer> shortestDistance = shortestDistanceCalculation.calculateShortestDistance(CITY_B, CITY_B);

    assertEquals(Optional.of(9), shortestDistance);
  }
}
