package by.ar.render;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class Tutorial1 {
  public static void main(String args[]) {
    Graph graph = new SingleGraph("grid");
    Generator gen = new GridGenerator(true, false);
    gen.addSink(graph);
    gen.begin();
    for (int i = 0; i < 100; i++) {
      gen.nextEvents();
    }
    gen.end();
    graph.display(false);
  }
}