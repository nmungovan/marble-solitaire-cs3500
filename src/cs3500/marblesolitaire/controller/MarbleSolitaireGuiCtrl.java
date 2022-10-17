package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

/**
 * Controller implementation. GUI.
 */
public class MarbleSolitaireGuiCtrl implements ControllerFeatures {

  private final MarbleSolitaireGuiView view;
  private final MarbleSolitaireModel model;
  private int fromRow = -1;
  private int fromCol = -1;

  public MarbleSolitaireGuiCtrl(MarbleSolitaireModel model, MarbleSolitaireGuiView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void ctrlInput(int row, int col) {
    if (fromRow == -1 && fromCol == -1) {
      fromRow = row;
      fromCol = col;
      return;
    }

    int toRow = row;
    int toCol = col;
    try {
      this.model.move(fromRow, fromCol, toRow, toCol);
      this.view.refresh();
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

}
