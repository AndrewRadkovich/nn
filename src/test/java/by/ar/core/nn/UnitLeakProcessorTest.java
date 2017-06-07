package by.ar.core.nn;

import by.ar.core.graph.Graph;
import by.ar.core.math.Functions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnitLeakProcessorTest {

  private static final double THRESHOLD = 1.5;

  @Test
  public void process() throws Exception {
    Graph<Integer, Neuron> neurons = new Graph<>();
    neurons.from(1).to(2, 3, 4);
    neurons.put(1, new Neuron(1.0, Functions.threshold(THRESHOLD), 0.9));
    neurons.put(2, new Neuron(0, Functions.threshold(THRESHOLD), 0.9));
    neurons.put(3, new Neuron(0, Functions.threshold(THRESHOLD), 0.9));
    neurons.put(4, new Neuron(1.4, Functions.threshold(THRESHOLD), 0.9));

    UnitLeakProcessor<Integer> processor = new UnitLeakProcessor<>();
    processor.process(neurons, 1);

    assertEquals(0.0, neurons.dataOf(1).charge, 0.001);
    assertEquals(0.333333333, neurons.dataOf(2).charge, 0.001);
    assertEquals(0.333333333, neurons.dataOf(3).charge, 0.001);
    assertEquals(THRESHOLD, neurons.dataOf(4).charge, 0.001);
  }
}
