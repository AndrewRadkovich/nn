package by.ar.core.math;

import java.util.function.Function;

public class Functions {
  public static Function<Double, Double> threshold(double threshold) {
    return aDouble -> {
      if (aDouble > threshold) return threshold;
      else return aDouble;
    };
  }
}
