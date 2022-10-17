package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

/**
 * This class represents operations that should be offered by a view for the Marble Solitaire game.
 * One object of {@code MarbleSolitaireTextView} represents one view of the Marble Solitaire game.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  protected final MarbleSolitaireModelState modelState;
  private final Appendable output;

  /**
   * Creates a view object with the given model state and an {@code Appendable} object to append
   * output to.
   *
   * @param modelState represents the model state to retrieve game state information from
   * @param output     represents the Appendable object to transmit output to
   * @throws IllegalArgumentException if the model state or output object is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable output)
      throws IllegalArgumentException {
    if (modelState == null) {
      throw new IllegalArgumentException("The model state cannot be null.");
    }
    if (output == null) {
      throw new IllegalArgumentException("The Appendable object provided cannot be null.");
    }
    this.modelState = modelState;
    this.output = output;
  }

  /**
   * Creates a {@code TriangleSolitaireTextView} with the given model state and displays it as text
   * (String) on the console {@code System.out}.
   *
   * @param modelState represents the model state of the game wanting to be viewed by the client
   * @throws IllegalArgumentException if the given model state is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState) {
    this(modelState, System.out);
  }

  @Override
  public String toString() {
    StringBuilder ans = new StringBuilder();
    int j;
    boolean seenNotInvalid;
    for (int i = 0; i < this.modelState.getBoardSize(); i++) {
      seenNotInvalid = false;
      for (j = 0; j < this.modelState.getBoardSize() - 1; j++) {
        if (this.modelState.getSlotAt(i, j) == SlotState.Invalid
            && seenNotInvalid) {
          break;
        }
        if (this.modelState.getSlotAt(i, j) != SlotState.Invalid) {
          seenNotInvalid = true;
        }
        if (j == 0) {
          ans.append(slotStateToString(this.modelState.getSlotAt(i, j)));
        } else {
          ans.append(" " + slotStateToString(this.modelState.getSlotAt(i, j)));
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

  /**
   * Render the board to the provided data destination. The board should be rendered exactly in the
   * format produced by the toString method above.
   *
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderBoard() throws IOException {
    output.append(this.toString());
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    output.append(message);
  }

  /**
   * Determines the String value of the given slot's SlotState in the board. Empty -> "_"; Marble ->
   * "O"; Invalid -> " "
   *
   * @param slotAt represents the slot's SlotState to be converted to String
   * @return the coordinating String value for the given SlotState
   * @throws IllegalArgumentException if the SlotState returned is not one of the three enumerations
   *                                  of SlotState
   */
  protected String slotStateToString(SlotState slotAt)
      throws IllegalArgumentException {
    switch (slotAt) {
      case Marble:
        return "O";
      case Empty:
        return "_";
      case Invalid:
        return " ";
      default:
        throw new IllegalArgumentException("SlotState provided was not one of the enumerations.");
    }
  }
}
