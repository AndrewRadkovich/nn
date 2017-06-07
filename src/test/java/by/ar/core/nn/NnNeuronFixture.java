package by.ar.core.nn;

import by.ar.core.graph.Graph;
import by.ar.core.math.Functions;

class NnNeuronFixture {
  static Graph<Integer, Neuron> initNN() {
    Graph<Integer, Neuron> neurons = new Graph<>();

    neurons.from(1).to(3, 4, 5);
    neurons.from(2).to(3, 4, 5);
    neurons.from(3).to(6);
    neurons.from(4).to(6);
    neurons.from(5).to(6);

    double threashold = 1.5;
    double leakLevel = 0.9;
    neurons.put(1, new Neuron(0, Functions.threshold(threashold), leakLevel));
    neurons.put(2, new Neuron(0, Functions.threshold(threashold), leakLevel));
    neurons.put(3, new Neuron(0, Functions.threshold(threashold), leakLevel));
    neurons.put(4, new Neuron(0, Functions.threshold(threashold), leakLevel));
    neurons.put(5, new Neuron(0, Functions.threshold(threashold), leakLevel));
    neurons.put(6, new Neuron(0, Functions.threshold(threashold), leakLevel));

    return neurons;
  }
}
