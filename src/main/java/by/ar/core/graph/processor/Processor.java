package by.ar.core.graph.processor;

import by.ar.core.graph.Graph;

public interface Processor<K, V> {
  boolean process(Graph<K, V> graph, K currentId);
}
