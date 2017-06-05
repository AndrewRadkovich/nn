package by.ar.core.graph.search;

import by.ar.core.graph.Graph;
import by.ar.core.graph.processor.Processor;

import java.util.*;

public class BreathFirstSearch<K, V> extends SearchAlgorithm<K, V> {

  private Map<K, Boolean> visited;
  private Queue<K> queue;
  private Graph<K, V> graph;

  public BreathFirstSearch(Graph<K, V> graph, Processor<K, V> processor) {
    super(processor);
    this.graph = graph;
  }

  private LinkedList<K> initQueueWithRoot(K rootId) {
    LinkedList<K> list = new LinkedList<>();
    list.add(rootId);
    return list;
  }

  private Map<K, Boolean> markAllAsNotVisited(Graph<K, V> graph) {
    Map<K, Boolean> map = new HashMap<>();
    for (K key : graph.nodeIds())
      map.put(key, false);
    return map;
  }

  @Override
  public void doSearch(K rootId) {
    visited = markAllAsNotVisited(graph);
    queue = initQueueWithRoot(rootId);
    while (!queue.isEmpty()) {
      K nodeId = next();
      boolean childrenAreToBeProcessed = process(nodeId);
      if (childrenAreToBeProcessed) {
        List<K> children = expand(nodeId);
        children.forEach(this::push);
      }
    }
  }

  private List<K> expand(K nodeId) {
    return graph.get(nodeId);
  }

  private void push(K nodeId) {
    if (!visited.get(nodeId))
      queue.add(nodeId);
  }

  private boolean process(K nodeId) {
    return processor.process(graph, nodeId);
  }

  private K next() {
    K nextNode = queue.poll();
    visited.put(nextNode, true);
    return nextNode;
  }
}
