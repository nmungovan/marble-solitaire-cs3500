package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class represents the model of a game of Triangle Marble Solitaire. One object represents one
 * game of Triangle Marble Solitaire.
 */
public class TriangleSolitaireModel extends StandardSolitaireModel implements MarbleSolitaireModel {

  /**
   * Creates a {@code TriangleSolitaireModel} with the standard dimension (5 rows) and an empty slot
   * at the top of the board (0, 0).
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  /**
   * Creates a {@code TriangleSolitaireModel} with the given dimension as the number of rows and an
   * empty slot at the top of the board (0, 0).
   *
   * @param size the number of rows on the board
   * @throws IllegalArgumentException if the size is negative or 0.
   */
  public TriangleSolitaireModel(int size) throws IllegalArgumentException {
    super(size, 0, 0);
  }

  /**
   * Creates a {@code TriangleSolitaireModel} with the standard dimension (5 rows) and an empty slot
   * at the given position (row, col), if valid.
   *
   * @param row the row value of the empty slot on the board
   * @param col the column value of the empty slot on the board
   * @throws IllegalArgumentException if the given value for an empty slot is out of bounds, or is
   *                                  invalid
   */
  public TriangleSolitaireModel(int row, int col) throws IllegalArgumentException {
    super(5, row, col);
  }

  /**
   * Creates a {@code TriangleSolitaireModel} with the given dimension as the number of rows and an
   * empty slot at the given position (row, col), if valid.
   *
   * @param size the number of rows on the board
   * @param row  the row value of the empty slot on the board
   * @param col  the column value of the empty slot on the board
   * @throws IllegalArgumentException if the size is not a positive integer or if the given
   *                                  coordinate pair is not valid or out of bounds of the board
   */
  public TriangleSolitaireModel(int size, int row, int col) throws IllegalArgumentException {
    super(size, row, col);
  }

  /**
   * Initializes and creates the board with a valid layout and an empty space at the specified
   * position for a {@code MarbleSolitaireModelState}.
   *
   * @param size     represents the number of rows of the board
   * @param emptyRow represents the x-coordinate of the empty slot of this board
   * @param emptyCol represents the y-coordinate of the empty slot of this board
   * @throws IllegalArgumentException if the marble is attempted to be put in a position that is
   *                                  {@code SlotState.Invalid}
   */
  @Override
  protected SlotState[][] createBoard(int size, int emptyRow, int emptyCol)
      throws IllegalArgumentException {
    SlotState[][] tempBoard = new SlotState[size][size];
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        if (col <= row) {
          tempBoard[row][col] = SlotState.Marble;
        } else {
          tempBoard[row][col] = SlotState.Invalid;
        }
      }
    }
    if (tempBoard[emptyRow][emptyCol] == SlotState.Invalid) {
      throw new IllegalArgumentException("This is an invalid coordinate.");
    }
    tempBoard[emptyRow][emptyCol] = SlotState.Empty;
    return tempBoard;
  }

  /**
   * Move a single marble from a given position to another given position. A move is only valid if:
   * The 'from' and 'to' positions are valid, there is a marble in the specified 'from' position,
   * the 'to' position is empty, the 'to' and 'from' positions are exactly two slots apart (either
   * on the same row and 2 columns apart, or along the diagonals), and there is a marble in the slot
   * between the 'to' and the 'from' positions. If valid, makes the move; otherwise, throws the
   * appropriate error.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible, as described above
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (Math.abs(fromRow - toRow) != 2 && Math.abs(fromRow - toRow) != 0) {
      throw new IllegalArgumentException("The 'from' and 'to' rows must be either the same or two" +
          " rows away.");
    }
    if (fromRow == toRow && Math.abs(fromCol - toCol) != 2) {
      throw new IllegalArgumentException("The 'from' and 'to' positions must be exactly two "
          + "positions away within the same row.");
    }
    if (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) != 0
        && Math.abs(fromCol - toCol) != 2) {
      throw new IllegalArgumentException("The 'from' and 'to' positions must be exactly two "
          + "positions away");
    }
    moveHelper(fromRow, fromCol, toRow, toCol);
  }

  /**
   * Checks if there are any open moves for a single marble on the board. The conditions for an open
   * move are: There is a marble in the current slot, there is a marble in the slot next to the
   * given slot, and the slot directly adjacent to that one in the same direction is empty. All
   * cells must be within bounds. PRECONDITION: The (row, col) value passed in contains a marble and
   * is not invalid.
   *
   * @param row  represents the given row of the slot that will check if any moves are valid
   * @param cell represents the given column of the slot that will check if any moves are valid
   * @return whether this slot has any valid moves that the player can make
   */
  @Override
  protected boolean checkNeighbors(int row, int cell) {
    if (validDims(row, cell - 2) && getSlotAt(row, cell - 2) == SlotState.Empty
        && getSlotAt(row, cell - 1) == SlotState.Marble) {
      return true;
    }
    if (validDims(row, cell + 2) && getSlotAt(row, cell + 2) == SlotState.Empty
        && getSlotAt(row, cell + 1) == SlotState.Marble) {
      return true;
    }
    if (validDims(row - 2, cell - 2) && getSlotAt(row - 1, cell - 1) == SlotState.Marble
        && getSlotAt(row - 2, cell - 2) == SlotState.Empty) {
      return true;
    }
    if (validDims(row - 2, cell) && getSlotAt(row - 1, cell) == SlotState.Marble
        && getSlotAt(row - 2, cell) == SlotState.Empty) {
      return true;
    }
    if (validDims(row + 2, cell) && getSlotAt(row + 1, cell) == SlotState.Marble
        && getSlotAt(row + 2, cell) == SlotState.Empty) {
      return true;
    }
    return (validDims(row + 2, cell + 2) && getSlotAt(row + 1, cell + 1) == SlotState.Marble
        && getSlotAt(row + 2, cell + 2) == SlotState.Empty);
  }
}
