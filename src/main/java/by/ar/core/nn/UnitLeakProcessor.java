package by.ar.core.nn;

import by.ar.core.graph.Graph;
import by.ar.core.graph.processor.Processor;

import java.util.List;

public class UnitLeakProcessor implements Processor<Integer, Unit> {

  @Override
  public boolean process(Graph<Integer, Unit> graph, Integer currentNodeId) {
    Unit currentUnit = graph.dataOf(currentNodeId);
    if (currentUnit.signalLevel > currentUnit.leakLevel) {
      List<Unit> children = graph.childrenOf(currentNodeId);
      int size = children.size();
      children.forEach(unit -> {
        unit.signalLevel = absorbFrom(currentUnit, size);
      });
    }
    return false;
  }

  private double absorbFrom(Unit currentUnit, int size) {
    double value = currentUnit.signalLevel / size;
    if (value > currentUnit.threashold) return currentUnit.threashold;
    else return value;
  }
}
