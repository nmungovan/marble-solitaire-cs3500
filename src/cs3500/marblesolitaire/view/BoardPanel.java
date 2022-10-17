package cs3500.marblesolitaire.view;

import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cs3500.marblesolitaire.controller.ControllerFeatures;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

/**
 * Board Panel class.
 */
public class BoardPanel extends JPanel implements Panel {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot;
  private Image marbleSlot;
  private Image blankSlot;
  final static int cellDimension = 50;
  static int originX;
  static int originY;

  /**
   * BoardPanel creation.
   *
   * @param state state passed in
   * @throws IllegalStateException when
   */
  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
          new Dimension((this.modelState.getBoardSize() + 4) * cellDimension
              , (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    originX = (int) (this.getPreferredSize().getWidth() / 2 - this.modelState.getBoardSize() *
        cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() / 2 - this.modelState.getBoardSize() *
        cellDimension / 2);

    //your code to the draw the board should go here. 
    //The originX and originY is the top-left of where the cell (0,0) should start
    //cellDimension is the width (and height) occupied by every cell

    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      for (int j = 0; j < this.modelState.getBoardSize(); j++) {
        Image image;
        if (modelState.getSlotAt(i, j) == SlotState.Marble) {
          image = marbleSlot;
        } else if (modelState.getSlotAt(i, j) == SlotState.Invalid) {
          image = blankSlot;
        } else {
          image = emptySlot;
        }
        g.drawImage(image, originX + (i * cellDimension), originY + (j * cellDimension),
            cellDimension, cellDimension, (img, infoFlags, x, y, width, height) -> false);
      }
    }
  }

  @Override
  public void panelInput(ControllerFeatures feature) {
    addMouseListener(new MouseListenView());
  }
}
