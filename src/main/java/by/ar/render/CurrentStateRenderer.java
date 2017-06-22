package by.ar.render;

import by.ar.render.view.model.NnView;
import by.ar.render.view.model.Position;

import java.awt.image.BufferedImage;

public class CurrentStateRenderer {

  public <K> BufferedImage nnToImage(NnView<K> nnView) {
    BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

    nnView.network.neurons().forEach((id, neuron) -> {
      Position pos = nnView.positions.get(id);
      image.setRGB(pos.x, pos.y, 1);
    });

    return image;
  }
}
