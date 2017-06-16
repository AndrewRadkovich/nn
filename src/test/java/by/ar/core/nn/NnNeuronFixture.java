package by.ar.core.nn;

import by.ar.core.graph.Graph;

import static by.ar.core.math.Functions.threshold;
import static by.ar.core.nn.NeuronFactory.neuron;

class NnNeuronFixture {

  static Graph<Integer, Neuron> initNN() {

    final double maxCharge = 1.5;
    final double leakLevel = 0.9;
    final double defaultWeight = 1.0;
    final int initialCharge = 0;

    Graph<Integer, Neuron> neurons = new Graph<>();

    neurons.from(1).weight(defaultWeight).to(3, 4, 5);
    neurons.from(2).weight(defaultWeight).to(3, 4, 5);
    neurons.from(3).weight(defaultWeight).to(6);
    neurons.from(4).weight(defaultWeight).to(6);
    neurons.from(5).weight(defaultWeight).to(6);

    neurons.put(1, neuron(initialCharge, threshold(maxCharge), leakLevel));
    neurons.put(2, neuron(initialCharge, threshold(maxCharge), leakLevel));
    neurons.put(3, neuron(initialCharge, threshold(maxCharge), leakLevel));
    neurons.put(4, neuron(initialCharge, threshold(maxCharge), leakLevel));
    neurons.put(5, neuron(initialCharge, threshold(maxCharge), leakLevel));
    neurons.put(6, neuron(initialCharge, threshold(maxCharge), leakLevel));

    return neurons;
  }
}
