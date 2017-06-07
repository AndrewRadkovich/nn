package by.ar.core.nn;

import java.util.function.Function;

public class Neuron {
  public double charge;
  public double leakLevel;
  public Function<Double, Double> func = Math::cos;

  public Neuron() {
  }

  public Neuron(double charge, Function<Double, Double> func, double leakLevel) {
    this.charge = charge;
    this.leakLevel = leakLevel;
    this.func = func;
  }

  @Override
  public String toString() {
    return "Neuron{" +
        "charge=" + charge +
        '}';
  }
}
