package by.ar.core.nn;

import java.util.function.Function;

public class NeuronFactory {
  public static Neuron neuron(double charge, Function<Double, Double> func, double leakLevel) {
    return new Neuron(charge, func, leakLevel);
  }
}
