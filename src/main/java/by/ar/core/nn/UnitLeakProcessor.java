package by.ar.core.nn;

import by.ar.core.graph.Graph;
import by.ar.core.graph.processor.Processor;
import org.apache.log4j.Logger;

import java.util.Map;

public class UnitLeakProcessor<K> implements Processor<K, Neuron> {

  private static final Logger log = Logger.getLogger(UnitLeakProcessor.class.getName());

  @Override
  public void process(Graph<K, Neuron> graph, K currentNodeId) {
    Neuron currentNeuron = graph.dataOf(currentNodeId);
    if (currentNeuron.charge >= currentNeuron.leakLevel) {
      log.debug("neuron[" + currentNodeId + "].children => " + graph.get(currentNodeId));
      Map<K, Neuron> children = graph.childrenWithIdsOf(currentNodeId);
      int size = children.size();
      if (size == 0) {
        log.debug(currentNodeId + " is a leaf, no need to process.");
        return;
      }
      children.forEach((id, neuron) -> {
        neuron.charge = neuron.func.apply(neuron.charge * graph.weight(currentNodeId, id) + currentNeuron.charge / size);
        log.debug(neuron.charge + " => neuron[" + id + "].charge");
      });
      log.debug("0.0 => neuron[" + currentNodeId + "].charge");
      currentNeuron.charge = 0.0;
    }
  }
}
