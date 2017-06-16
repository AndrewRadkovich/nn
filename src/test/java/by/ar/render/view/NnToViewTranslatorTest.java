package by.ar.render.view;

import by.ar.core.graph.Graph;
import by.ar.core.nn.NN;
import by.ar.core.nn.Neuron;
import by.ar.render.view.model.NnView;
import by.ar.render.view.model.Position;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class NnToViewTranslatorTest {

  @Test
  public void nnToView() throws Exception {
    Graph<Integer, Neuron> neuronGraph = new Graph<>();
    NN<Integer> nn = new NN<>(neuronGraph);
    NnView<Integer> nnView = NnToViewTranslator.nnToView(nn);
    Map<Integer, Position> positions = nnView.positions;
    assertEquals("[1, 1, 1]", positions.get(1).toString());
  }
}