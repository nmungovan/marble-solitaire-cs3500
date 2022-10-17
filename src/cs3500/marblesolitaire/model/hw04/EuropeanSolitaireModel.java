package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class represents the model of a game of European Marble Solitaire. One object represents one
 * game of European Marble Solitaire.
 */
public class EuropeanSolitaireModel extends EnglishSolitaireModel implements MarbleSolitaireModel {

  /**
   * Creates a {@code EuropeanSolitaireModel} with the standard dimensions (armLength == 3) and an
   * empty slot at the center of the board.
   */
  public EuropeanSolitaireModel() {
    super();
  }

  /**
   * Creates a {@code EuropeanSolitaireModel} with the given dimension as the length of the top row
   * and the empty slot in the center of the board.
   *
   * @param armLength represents the length of the top row (arm length)
   * @throws IllegalArgumentException if the given armLength is not a positive, odd number
   */
  public EuropeanSolitaireModel(int armLength) throws IllegalArgumentException {
    super(armLength);
  }

  /**
   * Creates a {@code SolitaireModel} with the standard dimensions (armLength == 3) and an empty
   * slot at the given slot on the board (sRow, sCol), if valid.
   *
   * @param sRow represents the row value of the empty slot on the board
   * @param sCol represents the column value of the empty slot on the board
   * @throws IllegalArgumentException if the given value for an empty slot is out of bounds, or is
   *                                  invalid
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(sRow, sCol);
  }

  /**
   * Creates a {@code EuropeanSolitaireModel} with the given dimension as the length of the top row
   * and the empty slot at the given position (emptyRow, emptyCol), if it is a valid position.
   *
   * @param armLength represents the length of the top row (arm length)
   * @param row       represents the row value of the empty slot on the board
   * @param col       represents the column value of the empty slot on the board
   * @throws IllegalArgumentException if the armLength is not a positive, odd number, or if the
   *                                  (row, col) coordinate pair is not valid or in bounds of the
   *                                  board
   */
  public EuropeanSolitaireModel(int armLength, int row, int col) {
    super(armLength, row, col);
  }

  /**
   * Initializes and creates the board with a valid layout and an empty space at the specified
   * position for a {@code EuropeanSolitaireModel}.
   *
   * @param armLength represents the length of the top/bottom row and leftmost/rightmost column
   * @param emptyRow  represents the x-coordinate of the empty slot of this board
   * @param emptyCol  represents the y-coordinate of the empty slot of this board
   * @throws IllegalArgumentException if the marble is attempted to be put in a position that is
   *                                  {@code SlotState.Invalid}
   */
  @Override
  protected SlotState[][] createBoard(int armLength, int emptyRow, int emptyCol)
      throws IllegalArgumentException {
    int length = Math.max(3, 3 * armLength - 2);
    int lowBound = (length / 2 - (armLength / 2));
    int highBound = (length / 2 + (armLength / 2));
    SlotState[][] tempBoard = new SlotState[length][length];
    for (int row = 0; row < length; row++) {
      for (int cell = 0; cell < length; cell++) {
        if (cell < lowBound || cell > highBound) {
          tempBoard[row][cell] = SlotState.Invalid;
        } else {
          tempBoard[row][cell] = SlotState.Marble;
        }
      }
      if (row < (length / 2 - (armLength / 2))) {
        lowBound--;
        highBound++;
      } else if (row >= (length / 2 + (armLength / 2))) {
        lowBound++;
        highBound--;
      }
    }
    if (tempBoard[emptyRow][emptyCol] != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid empty cell position (" + emptyRow + ", "
          + emptyCol + ")");
    }
    tempBoard[emptyRow][emptyCol] = SlotState.Empty;
    return tempBoard;
  }
}
