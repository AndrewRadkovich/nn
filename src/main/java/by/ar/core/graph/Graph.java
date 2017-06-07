package by.ar.core.graph;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Graph<K, V> extends Lookup<K, K> {
  private Map<K, V> idToData = new HashMap<>();
  private Set<K> ids = new HashSet<>();

  public ToWord<K, V> from(K from) {
    return new ToWord<>(from, this);
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

  public List<V> childrenOf(K nodeId) {
    List<K> list = get(nodeId);
    return list.stream().map(this::dataOf).collect(toList());
  }

  public V dataOf(K nodeId) {
    return idToData.get(nodeId);
  }

  public Set<K> nodeIds() {
    return ids;
  }

  public static class ToWord<K, V> {

    K from;
    Graph<K, V> graph;

    ToWord(K from, Graph<K, V> graph) {
      this.from = from;
      this.graph = graph;
    }

    @SafeVarargs
    public final void to(K... tos) {
      for (K to : tos) {
        graph.add(from, to);
      }
    }
  }
}
