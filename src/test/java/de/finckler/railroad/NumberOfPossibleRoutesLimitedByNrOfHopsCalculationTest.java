package de.finckler.railroad;

import static org.junit.Assert.assertEquals;

import static de.finckler.railroad.TestCityProvider.CITY_A;
import static de.finckler.railroad.TestCityProvider.CITY_B;
import static de.finckler.railroad.TestCityProvider.CITY_C;
import static de.finckler.railroad.TestRouteNetworkProvider.bidirectionalTwoCityRouteNetwork;
import static de.finckler.railroad.TestRouteNetworkProvider.officialRouteNetwork;

import org.junit.Test;

public class NumberOfPossibleRoutesLimitedByNrOfHopsCalculationTest {

  @Test
  public void expectToCalculateNumberOfRoutesWithOneRouteAndZeroHop() {
    final NumberOfPossibleRoutesLimitedByNrOfHopsCalculation calculation = new NumberOfPossibleRoutesLimitedByNrOfHopsCalculation(
      officialRouteNetwork());

    final int numberOfPossibleRoutes = calculation.calculateNumberOfPossibleRoutesLimitedByNrOfHops(CITY_A, CITY_B, 0, false);

    assertEquals(0, numberOfPossibleRoutes);
  }

  @Test
  public void expectToCalculateNumberOfRoutesWithOneRouteAndOneHop() {
    final NumberOfPossibleRoutesLimitedByNrOfHopsCalculation calculation = new NumberOfPossibleRoutesLimitedByNrOfHopsCalculation(
      officialRouteNetwork());

    final int numberOfPossibleRoutes = calculation.calculateNumberOfPossibleRoutesLimitedByNrOfHops(CITY_A, CITY_B, 1, false);

    assertEquals(1, numberOfPossibleRoutes);
  }

  @Test
  public void expectToCalculateNumberOfRoutesBetweenSameStartAndEnd() {
    final NumberOfPossibleRoutesLimitedByNrOfHopsCalculation calculation = new NumberOfPossibleRoutesLimitedByNrOfHopsCalculation(
      officialRouteNetwork());

    final int numberOfPossibleRoutes = calculation.calculateNumberOfPossibleRoutesLimitedByNrOfHops(CITY_C, CITY_C, 3, false);

    assertEquals(2, numberOfPossibleRoutes);
  }

  @Test
  public void expectToCalculateNumberOfRoutesWithBidirectionalRouteBetweenStartAndEnd() {
    final NumberOfPossibleRoutesLimitedByNrOfHopsCalculation calculation = new NumberOfPossibleRoutesLimitedByNrOfHopsCalculation(
      bidirectionalTwoCityRouteNetwork());

    final int numberOfPossibleRoutes = calculation.calculateNumberOfPossibleRoutesLimitedByNrOfHops(CITY_A, CITY_B, 1, false);

    assertEquals(1, numberOfPossibleRoutes);
  }

  @Test
  public void expectToCalculateNumberOfRoutesWithBidirectionalRouteBetweenSameStartAndEnd() {
    final NumberOfPossibleRoutesLimitedByNrOfHopsCalculation calculation = new NumberOfPossibleRoutesLimitedByNrOfHopsCalculation(
      bidirectionalTwoCityRouteNetwork());

    final int numberOfPossibleRoutes = calculation.calculateNumberOfPossibleRoutesLimitedByNrOfHops(CITY_A, CITY_A, 2, false);

    assertEquals(1, numberOfPossibleRoutes);
  }

  @Test
  public void expectToCalculateNumberOfRoutesWithBidirectionalRouteBetweenSameStartAndEndButOnlyOneHop() {
    final NumberOfPossibleRoutesLimitedByNrOfHopsCalculation calculation = new NumberOfPossibleRoutesLimitedByNrOfHopsCalculation(
      bidirectionalTwoCityRouteNetwork());

    final int numberOfPossibleRoutes = calculation.calculateNumberOfPossibleRoutesLimitedByNrOfHops(CITY_A, CITY_A, 1, false);

    assertEquals(0, numberOfPossibleRoutes);
  }

  @Test
  public void expectToCalculateNumberOfRoutesWithBidirectionalRouteBetweenSameStartAndEndByGoingSameRoutesTwice() {
    final NumberOfPossibleRoutesLimitedByNrOfHopsCalculation calculation = new NumberOfPossibleRoutesLimitedByNrOfHopsCalculation(
      bidirectionalTwoCityRouteNetwork());

    final int numberOfPossibleRoutes = calculation.calculateNumberOfPossibleRoutesLimitedByNrOfHops(CITY_A, CITY_A, 4, false);

    assertEquals(2, numberOfPossibleRoutes);
  }

  @Test
  public void expectToCalculateNumberOfRoutesWithBidirectionalRouteWithoutCountingShorterRoutes() {
    final NumberOfPossibleRoutesLimitedByNrOfHopsCalculation calculation = new NumberOfPossibleRoutesLimitedByNrOfHopsCalculation(
      bidirectionalTwoCityRouteNetwork());

    final int numberOfPossibleRoutes = calculation.calculateNumberOfPossibleRoutesLimitedByNrOfHops(CITY_A, CITY_A, 4, true);

    assertEquals(1, numberOfPossibleRoutes);
  }
}
