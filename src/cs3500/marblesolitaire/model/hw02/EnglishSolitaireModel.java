package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.StandardSolitaireModel;

/**
 * This class represents the English marble solitaire model. One object represents one model of the
 * game of English marble solitaire.
 */

public class EnglishSolitaireModel extends StandardSolitaireModel implements MarbleSolitaireModel {

  /**
   * Creates a {@code MarbleSolitaireModel} with the standard dimensions (armLength == 3) and an
   * empty slot at the center of the board.
   */
  public EnglishSolitaireModel() {
    this(3, 3);
  }

  /**
   * Creates a {@code SolitaireModel} with the given dimension as the length of the top row and the
   * empty slot in the center of the board.
   *
   * @param armLength represents the length of the top row (arm length)
   * @throws IllegalArgumentException if the given armLength is not a positive, odd number
   */
  public EnglishSolitaireModel(int armLength) throws IllegalArgumentException {
    this(armLength, Math.max(1, (3 * armLength - 2) / 2), Math.max(1, (3 * armLength - 2) / 2));
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
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
  }

  /**
   * Creates a {@code SolitaireModel} with the given dimension as the length of the top row and the
   * empty slot at the given position (emptyRow, emptyCol), if it is a valid position.
   *
   * @param armLength represents the length of the top row (arm length)
   * @param emptyRow  represents the row value of the empty slot on the board
   * @param emptyCol  represents the column value of the empty slot on the board
   * @throws IllegalArgumentException if the armLength is not a positive, odd number, or if the
   *                                  (emptyRow, emptyCol) coordinate pair is not valid or in bounds
   *                                  of the board
   */
  public EnglishSolitaireModel(int armLength, int emptyRow, int emptyCol)
      throws IllegalArgumentException {
    super(armLength, emptyRow, emptyCol);
  }

  /**
   * Constructor **used for testing** in which a SlotState 2-D array can be passed in.
   *
   * @param board represents the board that will be used instead of creating one.
   */
  public EnglishSolitaireModel(SlotState[][] board) {
    super(board);
  }

  /**
   * Move a single marble from a given position to another given position. A move is only valid if:
   * The 'from' and 'to' positions are valid, there is a marble in the specified 'from' position,
   * the 'to' position is empty, the 'to' and 'from' positions are exactly two slots apart
   * (horizontally or vertically, no diagonals), and there is a marble in the slot between the 'to'
   * and the 'from' positions. If valid, makes the move; otherwise, throws the appropriate error.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible, as described above
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!(Math.abs(toRow - fromRow) == 2 && toCol - fromCol == 0)
        && !(Math.abs(toCol - fromCol) == 2 && toRow - fromRow == 0)) {
      throw new IllegalArgumentException("The 'from' and 'to' positions must be exactly two "
          + "positions away, horizontally or vertically.");
    }
    moveHelper(fromRow, fromCol, toRow, toCol);
  }

  /**
   * Checks if there are any open moves for a single marble on the board. The conditions for an open
   * move are: There is a marble in the current slot, there is a marble in the slot next to the
   * given slot, and the slot directly adjacent to that one in the same direction is empty. All
   * cells must be within bounds.
   * PRECONDITION: The (row, col) value passed in contains a marble and is not invalid.
   *
   * @param row  represents the given row of the slot that will check if any moves are valid
   * @param cell represents the given column of the slot that will check if any moves are valid
   * @return whether this slot has any valid moves that the player can make
   */
  @Override
  protected boolean checkNeighbors(int row, int cell) {
    if (validDims(row - 1, cell) && validDims(row - 2, cell)
        && this.board[row - 1][cell] == SlotState.Marble
        && this.board[row - 2][cell] == SlotState.Empty) {
      return true;
    }
    if (validDims(row + 1, cell) && validDims(row + 2, cell)
        && this.board[row + 1][cell] == SlotState.Marble
        && this.board[row + 2][cell] == SlotState.Empty) {
      return true;
    }
    if (validDims(row, cell - 1) && validDims(row, cell - 2)
        && this.board[row][cell - 1] == SlotState.Marble
        && this.board[row][cell - 2] == SlotState.Empty) {
      return true;
    }
    return validDims(row, cell + 1) && validDims(row, cell + 2)
        && this.board[row][cell + 1] == SlotState.Marble
        && this.board[row][cell + 2] == SlotState.Empty;
  }

  /**
   * Initializes and creates the board with a valid layout and an empty space at the specified
   * position for a {@code MarbleSolitaireModel}.
   *
   * @param armLength represents the length of the top/bottom row
   * @param emptyRow  represents the x-coordinate of the empty slot of this board
   * @param emptyCol  represents the y-coordinate of the empty slot of this board
   * @throws IllegalArgumentException if the marble is attempted to be put in a position that is
   *                                  {@code SlotState.Invalid}
   */
  protected SlotState[][] createBoard(int armLength, int emptyRow, int emptyCol)
      throws IllegalArgumentException {
    int length = Math.max(3, 3 * armLength - 2);
    SlotState[][] tempBoard = new SlotState[length][length];
    for (int row = 0; row < length; row++) {
      if (row < (length / 2 - (armLength / 2)) || row > (length / 2 + (armLength / 2))) {
        for (int cell = 0; cell < length; cell++) {
          if (cell < (length / 2 - (armLength / 2)) || cell > (length / 2 + (armLength / 2))) {
            tempBoard[row][cell] = SlotState.Invalid;
          } else {
            tempBoard[row][cell] = SlotState.Marble;
          }
        }
      } else {
        for (int cell = 0; cell < tempBoard.length; cell++) {
          tempBoard[row][cell] = SlotState.Marble;
        }
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
