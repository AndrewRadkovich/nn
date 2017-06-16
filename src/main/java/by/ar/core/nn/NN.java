package by.ar.core.nn;

import by.ar.core.graph.Graph;
import by.ar.core.graph.search.BreathFirstSearch;
import by.ar.core.graph.search.SearchAlgorithm;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.function.Predicate;

import static by.ar.core.math.Functions.threshold;

public class NN<K> {

  private static final Logger log = Logger.getLogger(NN.class.getName());

  private Graph<K, Neuron> neurons = new Graph<>();
  private SearchAlgorithm<K, Neuron> traverseAlgorithm;
  private K[] inputNeurons;
  private K[] outputNeurons;

  public NN(Graph<K, Neuron> neurons) {
    this.neurons = neurons;
    traverseAlgorithm = new BreathFirstSearch<>(this.neurons, new UnitLeakProcessor<>());
    Predicate<Neuron> signalIsWeak = neuron -> neuron.charge < neuron.charge;
    traverseAlgorithm.stopWhen(signalIsWeak);
  }

  @SafeVarargs
  public final NN<K> input(K... neuronIds) {
    this.inputNeurons = neuronIds;
    return this;
  }

  @SafeVarargs
  public final NN<K> output(K... neuronIds) {
    this.outputNeurons = neuronIds;
    return this;
  }

  public double[] sim(double[] inputSignals) {
    log.debug("Simulation is started with input: " + Arrays.toString(inputSignals));
    chargeInputsWith(inputSignals);
    diffuseImpulse();
    return outputSignals();
  }

  public double chargeOf(K neuronId) {
    return neurons.dataOf(neuronId).charge;
  }

  public Graph<K, Neuron> neurons() {
    return neurons;
  }

  private void diffuseImpulse() {
    Queue<List<K>> queue = new LinkedList<>();
    queue.add(Arrays.asList(inputNeurons));
    while (!queue.isEmpty()) {
      List<K> last = queue.poll();
      queue.addAll(traverseFromLast(last));
    }
  }

  private Collection<? extends List<K>> traverseFromLast(List<K> last) {
    List<List<K>> newLast = new ArrayList<>();
    for (K nodeId : last) {
      List<K> travel = traverseAlgorithm.travel(nodeId);
      newLast.add(travel);
    }
    return newLast;
  }

  private double[] outputSignals() {
    double[] outputSignals = new double[outputNeurons.length];
    for (int i = 0; i < outputNeurons.length; i++) {
      outputSignals[i] = neurons.dataOf(outputNeurons[i]).charge;
      neurons.dataOf(outputNeurons[i]).charge = 0.0;
    }
    return outputSignals;
  }

  private void chargeInputsWith(double[] inputSignals) {
    int i = 0;
    for (K inputNeuron : inputNeurons) {
      neurons.put(inputNeuron, new Neuron(inputSignals[i++], threshold(1.5), 0.9));
    }
  }
}
