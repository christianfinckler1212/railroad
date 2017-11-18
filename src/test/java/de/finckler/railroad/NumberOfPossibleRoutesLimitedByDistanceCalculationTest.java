package de.finckler.railroad;

import static org.junit.Assert.assertEquals;

import static de.finckler.railroad.TestCityProvider.CITY_A;
import static de.finckler.railroad.TestCityProvider.CITY_B;
import static de.finckler.railroad.TestCityProvider.CITY_C;
import static de.finckler.railroad.TestRouteNetworkProvider.bidirectionalTwoCityRouteNetwork;
import static de.finckler.railroad.TestRouteNetworkProvider.officialRouteNetwork;

import org.junit.Test;

public class NumberOfPossibleRoutesLimitedByDistanceCalculationTest {

  @Test
  public void expectToCalculateNumberOfRoutesWithOneRouteAndTooShortDistance() {
    final NumberOfPossibleRoutesLimitedByDistanceCalculation calculation = new NumberOfPossibleRoutesLimitedByDistanceCalculation(
      bidirectionalTwoCityRouteNetwork());

    final int numberOfPossibleRoutes = calculation.calculateNumberOfPossibleRoutesByDistance(CITY_A, CITY_B, 0);

    assertEquals(0, numberOfPossibleRoutes);
  }

  @Test
  public void expectToCalculateNumberOfRoutesWithOneRouteAndExactlyFittingDistance() {
    final NumberOfPossibleRoutesLimitedByDistanceCalculation calculation = new NumberOfPossibleRoutesLimitedByDistanceCalculation(
      bidirectionalTwoCityRouteNetwork());

    final int numberOfPossibleRoutes = calculation.calculateNumberOfPossibleRoutesByDistance(CITY_A, CITY_B, 5);

    assertEquals(1, numberOfPossibleRoutes);
  }

  @Test
  public void expectToCalculateNumberOfRoutesWithBidirectionalRouteAndExactlyFittingDistance() {
    final NumberOfPossibleRoutesLimitedByDistanceCalculation calculation = new NumberOfPossibleRoutesLimitedByDistanceCalculation(
      bidirectionalTwoCityRouteNetwork());

    final int numberOfPossibleRoutes = calculation.calculateNumberOfPossibleRoutesByDistance(CITY_A, CITY_A, 10);

    assertEquals(1, numberOfPossibleRoutes);
  }

  @Test
  public void expectToCalculateNumberOfRoutesWithVeryLongDistance() {
    final NumberOfPossibleRoutesLimitedByDistanceCalculation calculation = new NumberOfPossibleRoutesLimitedByDistanceCalculation(
      officialRouteNetwork());

    final int numberOfPossibleRoutes = calculation.calculateNumberOfPossibleRoutesByDistance(CITY_C, CITY_C, 29);

    assertEquals(7, numberOfPossibleRoutes);
  }
}
