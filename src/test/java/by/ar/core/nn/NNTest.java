package by.ar.core.nn;

import org.junit.Test;

import static org.junit.Assert.*;

public class NNTest {

  @Test
  public void testImpulseDiffusion() throws Exception {
    Integer[] outputNeuronIds = {5, 6};
    Integer[] inputNeuronIds = {1, 2, 3};
    NN<Integer> net = new NN<Integer>()
        .input(inputNeuronIds)
        .output(outputNeuronIds);

    double[] inputSignals = new double[] {1.0, 0.7, 0.35};
    double[] outputSignals = net.sim(inputSignals);

    assertEquals(outputNeuronIds.length, outputSignals.length);
    assertNotNull(outputSignals);
  }
}