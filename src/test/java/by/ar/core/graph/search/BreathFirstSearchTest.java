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
      return true;
    };
    new BreathFirstSearch<>(graph, processor).doSearch(1);
    assertEquals("1 2 3 4 5 6 7 ", sb.toString());
  }

  @Test
  public void doSearch() throws Exception {
    Graph<Integer, String> graph = new Graph<>();
    graph.from(1).to(6, 7);
    graph.from(2).to(6);
    graph.from(3).to(7);
    graph.from(4).to(7, 8, 9);
    graph.from(5).to(7);
    graph.from(6).to(10, 11);
    graph.from(7).to(12);
    graph.from(8).to(13, 14);
    graph.from(9).to(12, 13);
    graph.from(10).to(15);
    graph.from(11).to();
    graph.from(12).to(15);
    graph.from(13).to(16, 17);
    graph.from(14).to(13);
    graph.from(15).to(18);
    graph.from(16).to(19);
    graph.from(17).to(19);
    graph.from(18).to(20, 21);
    graph.from(19).to(20, 21, 22);

    Processor<Integer, String> processor = (graph1, currentId) -> {
      System.out.print(currentId + " ");
      return true;
    };
    new BreathFirstSearch<>(graph, processor).doSearch(1);
  }
}
