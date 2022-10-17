import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;

/**
 * Test class that holds methods to test board logic and the model methods.
 */
public abstract class ATestModelClass {

  abstract void assertBoard(MarbleSolitaireModel model, int row, int col);

  void assertMove(MarbleSolitaireModel model, int fromRow, int fromCol, int toRow,
                  int toCol) {
    int mRow = (fromRow + toRow) / 2;
    int mCol = (fromCol + toCol) / 2;
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(fromRow, fromCol));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(mRow, mCol));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(toRow, toCol));
    model.move(fromRow, fromCol, toRow, toCol);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(fromRow, fromCol));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(mRow, mCol));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(toRow, toCol));
  }

}
