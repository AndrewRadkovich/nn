package by.ar.render.view.model;

import by.ar.core.nn.NN;

import java.util.HashMap;
import java.util.Map;

public class NnView<K> {
  public Map<K, Position> positions = new HashMap<>();
  public NN<K> network;
}
