package by.ar.core.nn;

public class Neuron {
  public double signalLevel;
  public double threashold;
  public double leakLevel;

  public Neuron() {
  }

  public Neuron(double signalLevel, double threashold, double leakLevel) {
    this.signalLevel = signalLevel;
    this.threashold = threashold;
    this.leakLevel = leakLevel;
  }
}
