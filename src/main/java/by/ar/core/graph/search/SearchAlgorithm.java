package by.ar.core.graph.search;

import by.ar.core.graph.processor.Processor;

import java.util.List;
import java.util.function.Predicate;

public abstract class SearchAlgorithm<K, V> {

  protected Processor<K, V> processor;
  protected Predicate<V> stopCondition = node -> false;

  public SearchAlgorithm(Processor<K, V> processor) {
    this.processor = processor;
  }

  abstract public List<K> travel(K rootId);

  public void stopWhen(Predicate<V> stopCondition) {
    this.stopCondition = stopCondition;
  }

  protected boolean shouldStop(V value) {
    return stopCondition.test(value);
  }
}
