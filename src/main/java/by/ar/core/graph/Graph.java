package by.ar.core.graph;

import java.util.*;

public class Graph<K, V> extends Lookup<K, K> {
  private Map<K, V> idToData = new HashMap<>();
  private Map<K, Map<K, Double>> weights = new HashMap<>();
  private Set<K> ids = new HashSet<>();

  public WeightOrToWord<K, V> from(K from) {
    return new WeightOrToWord<>(from, this);
  }

  @Override
  public void add(K parent, K child) {
    super.add(parent, child);
    ids.add(parent);
    ids.add(child);
  }

  public void put(K key, V value) {
    idToData.put(key, value);
  }

  public Map<K, V> childrenWithIdsOf(K nodeId) {
    List<K> list = get(nodeId);
    HashMap<K, V> map = new HashMap<>();
    list.forEach(id -> map.put(id, dataOf(id)));
    return map;
  }

  public V dataOf(K nodeId) {
    return idToData.get(nodeId);
  }

  public Set<K> nodeIds() {
    return ids;
  }

  public double weight(K from, K to) {
    return weights.get(from).get(to);
  }

  public static class ToWord<K, V> {

    K from;
    double weight;
    Graph<K, V> graph;

    ToWord(K from, Graph<K, V> graph) {
      this.from = from;
      this.graph = graph;
      this.weight = 1.0;
    }

    ToWord(K from, Graph<K, V> graph, double weight) {
      this.from = from;
      this.graph = graph;
      this.weight = weight;
    }

    @SafeVarargs
    public final void to(K... tos) {
      Map<K, Double> weightsToChildren;
      if (graph.weights.containsKey(from)) {
        weightsToChildren = graph.weights.get(from);
      } else {
        graph.weights.put(from, weightsToChildren = new HashMap<>());
      }
      for (K to : tos) {
        graph.add(from, to);
        weightsToChildren.put(to, weight);
      }
    }
  }

  public static class WeightOrToWord<K, V> extends ToWord<K, V> {

    WeightOrToWord(K from, Graph<K, V> graph) {
      super(from, graph);
    }

    public final ToWord<K, V> weight(double weight) {
      return new ToWord<>(from, graph, weight);
    }
  }
}
