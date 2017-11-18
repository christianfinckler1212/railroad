package de.finckler.railroad.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class Route {
  private City start;
  private City end;
  private int distance;
}
