package by.ar.core.nn;

import by.ar.core.graph.Graph;

public class NN<K> {
  private Graph<K, Neuron> neurons = new Graph<>();
  private K[] inputNeurons;
  private K[] outputNeurons;

  public NN<K> input(K... neuronIds) {
    this.inputNeurons = neuronIds;
    return this;
  }

  public NN<K> output(K... neuronIds) {
    this.outputNeurons = neuronIds;
    return this;
  }

  public double[] sim(double[] inputSignals) {
    chargeInputsWith(inputSignals);
    diffuseInputSignals();
    return outputSignals();
  }

  private void diffuseInputSignals() {
    //TODO
  }

  private double[] outputSignals() {
    double[] outputSignals = new double[outputNeurons.length];
    for (int i = 0; i < outputNeurons.length; i++) {
      outputSignals[i] = neurons.dataOf(outputNeurons[i]).signalLevel;
    }
    return outputSignals;
  }

  private void chargeInputsWith(double[] inputSignals) {
    int i = 0;
    for (K inputNeuron : inputNeurons) {
      neurons.put(inputNeuron, new Neuron(inputSignals[i++], 1.5, 0.9));
    }
  }
}
