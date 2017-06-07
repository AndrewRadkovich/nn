package by.ar.core.graph.search;

import by.ar.core.graph.Graph;
import by.ar.core.graph.processor.Processor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BreathFirstSearchTest {

  @Test
  public void checkCorrectTraverseOrder() throws Exception {
    Graph<Integer, String> graph = new Graph<>();

    graph.from(1).to(2, 3);
    graph.from(2).to(4);
    graph.from(3).to(5, 6);
    graph.from(5).to(7);

    StringBuilder sb = new StringBuilder();
    Processor<Integer, String> processor = (graph1, currentId) -> {
      sb.append(currentId).append(" ");
    };
    new BreathFirstSearch<>(graph, processor).travel(1);
    assertEquals("1 2 3 4 5 6 7 ", sb.toString());
  }

  @Test
  public void testShouldStop() throws Exception {
    Graph<Integer, String> graph = new Graph<>();

    graph.from(1).to(2, 3);
    graph.from(2).to(4, 5);
    graph.from(3).to(6);

    graph.put(1, "A");
    graph.put(2, "AQ");
    graph.put(3, "A");
    graph.put(4, "A");
    graph.put(5, "A");
    graph.put(6, "A");

    StringBuilder sb = new StringBuilder();
    Processor<Integer, String> processor = (graph1, currentId) -> {
      sb.append(currentId).append(" ");
    };
    BreathFirstSearch<Integer, String> breathFirstSearch = new BreathFirstSearch<>(graph, processor);
    breathFirstSearch.stopWhen(value -> value.contains("Q"));
    breathFirstSearch.travel(1);
    assertEquals("1 2 3 6 ", sb.toString());
  }
}
