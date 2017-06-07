package by.ar.core.nn;

import org.junit.Test;

import static by.ar.core.nn.NnNeuronFixture.initNN;
import static org.junit.Assert.assertEquals;

public class NNTest {

  @Test
  public void testImpulseDiffusion() throws Exception {
    Integer[] outputNeuronIds = {6};
    Integer[] inputNeuronIds = {1, 2};
    NN<Integer> net = new NN<>(initNN())
        .input(inputNeuronIds)
        .output(outputNeuronIds);

    double[] inputSignals = {0.9, 0.9, 0.9};
    double[] output1 = net.sim(inputSignals);

    assertEquals(0.0, net.chargeOf(1), 0.001);
    assertEquals(0.0, net.chargeOf(2), 0.001);
    assertEquals(0.6, net.chargeOf(3), 0.001);
    assertEquals(0.6, net.chargeOf(4), 0.001);
    assertEquals(0.6, net.chargeOf(5), 0.001);
    assertEquals(0.0, output1[0], 0.001);

    double[] output2 = net.sim(inputSignals);

    assertEquals(0.0, net.chargeOf(1), 0.001);
    assertEquals(0.0, net.chargeOf(2), 0.001);
    assertEquals(0.0, net.chargeOf(3), 0.001);
    assertEquals(0.0, net.chargeOf(4), 0.001);
    assertEquals(0.0, net.chargeOf(5), 0.001);
    assertEquals(1.5, output2[0], 0.001);

    double[] output3 = net.sim(inputSignals);

    assertEquals(0.0, net.chargeOf(1), 0.001);
    assertEquals(0.0, net.chargeOf(2), 0.001);
    assertEquals(0.6, net.chargeOf(3), 0.001);
    assertEquals(0.6, net.chargeOf(4), 0.001);
    assertEquals(0.6, net.chargeOf(5), 0.001);
    assertEquals(0.0, output3[0], 0.001);
  }
}
