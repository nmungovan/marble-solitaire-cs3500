package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

/**
 * This class represents operations that should be offered by a view for the Triangle Marble
 * Solitaire game. One object of {@code MarbleSolitaireTextView} represents one view of the Triangle
 * Marble Solitaire game.
 */
public class TriangleSolitaireTextView extends MarbleSolitaireTextView implements
    MarbleSolitaireView {

  /**
   * Creates a view object with the given model state and an {@code Appendable} object to append
   * output to.
   *
   * @param modelState represents the model state to retrieve game state information from
   * @param output     represents the Appendable object to transmit output to
   * @throws IllegalArgumentException if the model state or output object is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable output) throws
      IllegalArgumentException {
    super(modelState, output);
  }

  /**
   * Creates a {@code TriangleSolitaireTextView} with the given model state and displays it as text
   * (String) on the console {@code System.out}.
   *
   * @param modelState represents the model state of the game wanting to be viewed by the client
   * @throws IllegalArgumentException if the given model state is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState modelState) throws
      IllegalArgumentException {
    super(modelState);
  }

  @Override
  public String toString() {
    StringBuilder ans = new StringBuilder();
    boolean seenNotInvalid;
    int j;
    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      seenNotInvalid = false;
      ans.append(" ".repeat(Math.max(0, this.modelState.getBoardSize() - i - 1)));
      for (j = 0; j < this.modelState.getBoardSize() - 1; j++) {
        SlotState target = this.modelState.getSlotAt(i, j);
        if (target == SlotState.Invalid && seenNotInvalid) {
          break;
        }
        if (target != SlotState.Invalid) {
          seenNotInvalid = true;
        }
        if (j == 0) {
          ans.append(slotStateToString(target));
        } else {
          ans.append(" " + slotStateToString(target));
        }
      }
      if (this.modelState.getSlotAt(i, j) != SlotState.Invalid) {
        ans.append(" " + slotStateToString(this.modelState.getSlotAt(i, j)));
      }
      if (i != this.modelState.getBoardSize() - 1) {
        ans.append("\n");
      }
    }
    return ans.toString();
  }
}
