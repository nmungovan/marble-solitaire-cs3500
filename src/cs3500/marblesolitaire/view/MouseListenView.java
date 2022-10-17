package cs3500.marblesolitaire.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * MouseListener.
 */
public class MouseListenView extends MouseAdapter {

  @Override
  public void mouseClicked(MouseEvent e) {
    int x = (e.getX() - BoardPanel.originX) / BoardPanel.cellDimension;
    int y = (e.getY() - BoardPanel.originY) / BoardPanel.cellDimension;
  }
}
