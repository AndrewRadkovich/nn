package by.ar.core.graph.search;

import by.ar.core.graph.processor.Processor;

public abstract class SearchAlgorithm<K, V> {

  protected Processor<K, V> processor;

  public SearchAlgorithm(Processor<K, V> processor) {
    this.processor = processor;
  }

  abstract public void doSearch(K rootId);
}
