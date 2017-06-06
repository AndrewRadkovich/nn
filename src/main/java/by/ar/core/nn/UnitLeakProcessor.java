package by.ar.core.nn;

import by.ar.core.graph.Graph;
import by.ar.core.graph.processor.Processor;

import java.util.List;

public class UnitLeakProcessor implements Processor<Integer, Neuron> {

  @Override
  public boolean process(Graph<Integer, Neuron> graph, Integer currentNodeId) {
    Neuron currentNeuron = graph.dataOf(currentNodeId);
    if (currentNeuron.signalLevel > currentNeuron.leakLevel) {
      List<Neuron> children = graph.childrenOf(currentNodeId);
      int size = children.size();
      children.forEach(neuron -> {
        neuron.signalLevel = absorbFrom(currentNeuron, size);
      });
    }
    return false;
  }

  private double absorbFrom(Neuron currentNeuron, int size) {
    double value = currentNeuron.signalLevel / size;
    if (value > currentNeuron.threashold) return currentNeuron.threashold;
    else return value;
  }
}
