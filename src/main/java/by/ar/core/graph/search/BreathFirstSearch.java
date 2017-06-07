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
  public List<K> travel(K rootId) {
    List<K> lastNodes = new LinkedList<>();
    visited = markAllAsNotVisited(graph);
    queue = initQueueWithRoot(rootId);
    while (!queue.isEmpty()) {
      K nodeId = next();
      process(nodeId);
      if (shouldStop(graph.dataOf(nodeId))) {
        lastNodes.add(nodeId);
        continue;
      }
      List<K> children = expand(nodeId);
      children.forEach(this::push);
    }
    return lastNodes;
  }

  private List<K> expand(K nodeId) {
    return graph.get(nodeId);
  }

  private void push(K nodeId) {
    if (!visited.get(nodeId))
      queue.add(nodeId);
  }

  private void process(K nodeId) {
    processor.process(graph, nodeId);
  }

  private K next() {
    K nextNode = queue.poll();
    visited.put(nextNode, true);
    return nextNode;
  }
}
