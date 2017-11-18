package de.finckler.railroad;

import static java.util.Arrays.asList;

import static org.junit.Assert.assertEquals;

import static de.finckler.railroad.TestCityProvider.CITY_A;
import static de.finckler.railroad.TestCityProvider.CITY_B;
import static de.finckler.railroad.TestCityProvider.CITY_C;
import static de.finckler.railroad.TestCityProvider.CITY_D;
import static de.finckler.railroad.TestCityProvider.CITY_E;
import static de.finckler.railroad.TestRouteNetworkProvider.officialRouteNetwork;

import java.util.Optional;

import org.junit.Test;

public class TripDistanceCalculationTest {

  @Test
  public void expectToCalculateDistanceOfTwoCities() {
    final TripDistanceCalculation tripDistanceCalculation = new TripDistanceCalculation(officialRouteNetwork());

    final Optional<Integer> resultedDistance = tripDistanceCalculation.getTripDistance(asList(CITY_A, CITY_D));

    assertEquals(Optional.of(5), resultedDistance);
  }

  @Test
  public void expectToCalculateDistanceOfThreeCities() {
    final TripDistanceCalculation tripDistanceCalculation = new TripDistanceCalculation(officialRouteNetwork());

    final Optional<Integer> resultedDistance = tripDistanceCalculation.getTripDistance(asList(CITY_A, CITY_B,
      CITY_C));

    assertEquals(Optional.of(9), resultedDistance);
  }

  @Test
  public void expectToCalculateDistanceOfThreeCities2() {
    final TripDistanceCalculation tripDistanceCalculation = new TripDistanceCalculation(officialRouteNetwork());

    final Optional<Integer> resultedDistance = tripDistanceCalculation.getTripDistance(
      asList(CITY_A, CITY_D, CITY_C));

    assertEquals(Optional.of(13), resultedDistance);
  }

  @Test
  public void expectToCalculateDistanceOfFiveCities() {
    final TripDistanceCalculation tripDistanceCalculation = new TripDistanceCalculation(officialRouteNetwork());

    final Optional<Integer> resultedDistance = tripDistanceCalculation.getTripDistance(
      asList(CITY_A, CITY_E, CITY_B, CITY_C, CITY_D));

    assertEquals(Optional.of(22), resultedDistance);
  }

  @Test
  public void expectNotToCalculateDistanceOfNotConnectedCities() {
    final TripDistanceCalculation tripDistanceCalculation = new TripDistanceCalculation(officialRouteNetwork());

    final Optional<Integer> resultedDistance = tripDistanceCalculation.getTripDistance(
      asList(CITY_A, CITY_E, CITY_D));

    assertEquals(Optional.empty(), resultedDistance);
  }
}
