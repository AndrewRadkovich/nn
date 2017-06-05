package by.ar.core.graph;

import java.util.*;

public class Lookup<K, V> {

  private Map<K, LinkedList<V>> parentToChild = new HashMap<>();

  public List<V> get(K key) {
    return parentToChild.getOrDefault(key, new LinkedList<>());
  }

  public void add(K parent, V child) {
    if (parentToChild.containsKey(parent))
      parentToChild.get(parent).add(child);
    else {
      LinkedList<V> list = new LinkedList<>();
      list.add(child);
      parentToChild.put(parent, list);
    }
  }

  public Set<K> keySet() {
    return parentToChild.keySet();
  }

  public boolean has(K key) {
    return parentToChild.containsKey(key);
  }
}
