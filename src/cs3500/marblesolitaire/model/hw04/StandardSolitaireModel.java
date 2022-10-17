package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Abstract class that holds common logic and methods for all implementations of models for {@code
 * MarbleSolitaire}. Some implementations may override methods or add methods based on how
 * gameplay occurs. One {@code StandardSolitaireModel }object represents one game model of Marble
 * Solitaire.
 */
public abstract class StandardSolitaireModel implements MarbleSolitaireModel {

  protected final SlotState[][] board;

  /**
   * Creates an abstract {@code StandardSolitaireModel} with a given size and an empty slot at
   * (emptyRow, emptyCol) on the board, if valid. Specific implementations have different uses
   * for these values.
   *
   * @param size the size of the board, different for each implementation
   * @param emptyRow the row value of the empty slot on the board
   * @param emptyCol the column value of the empty slot on the board
   * @throws IllegalArgumentException if the size is negative or the empty slot coordinate pair
   *                                  is out of bounds. Specific implementations may throw other
   *                                  exceptions.
   */
  public StandardSolitaireModel(int size, int emptyRow, int emptyCol) throws
      IllegalArgumentException {
    if (size <= 0) {
      throw new IllegalArgumentException("Size must be a positive number.");
    }
    if (emptyRow < 0 || emptyCol < 0) {
      throw new IllegalArgumentException("Coordinates cannot be negative.");
    }
    if (this instanceof EnglishSolitaireModel) {
      if (size % 2 == 0) {
        throw new IllegalArgumentException("Length must be an odd number.");
      }
      int length = Math.max(3, 3 * size - 2);
      if (emptyRow >= length || emptyCol >= length) {
        throw new IllegalArgumentException("The inputted dimension is beyond the dimensions of the "
            + "board.");
      }
    } else if (this instanceof TriangleSolitaireModel) {
      if (emptyRow >= size || emptyCol >= size) {
        throw new IllegalArgumentException("The inputted dimension is beyond the dimensions of the "
            + "board.");
      }
    }
    this.board = createBoard(size, emptyRow, emptyCol);
  }

  /**
   * Constructor **used for testing** in which a SlotState 2-D array can be passed in.
   *
   * @param board represents the board that will be used instead of creating one.
   */
  public StandardSolitaireModel(SlotState[][] board) {
    this.board = board;
  }

  /**
   * Initializes and creates the board with a valid layout and an empty space at the specified
   * position for an implementation of the {@code StandardSolitaireModel}.
   *
   * @param size represents the length of the top/bottom row
   * @param row  represents the x-coordinate of the empty slot of this board
   * @param col  represents the y-coordinate of the empty slot of this board
   * @throws IllegalArgumentException if the marble is attempted to be put in a position that is
   *                                  {@code SlotState.Invalid}
   */
  protected abstract SlotState[][] createBoard(int size, int row, int col) throws
      IllegalArgumentException;

  public abstract void move(int fromRow, int fromCol, int toRow, int toCol) throws
      IllegalArgumentException;

  /**
   * Abstracted exception checks for all known implementations of {@code MarbleSolitaire},
   * specific implementations may restrict moves more heavily. Also makes the move on the board.
   * PRECONDITION: Other restrictions are checked before this method is called.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if there is no marble in the 'from' position, if the 'to'
   *                                  position is not empty, or if there is no marble between the
   *                                  'from' position and the 'to' position.
   */
  protected void moveHelper(int fromRow, int fromCol, int toRow, int toCol) throws
      IllegalArgumentException {
    if (getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2) != SlotState.Marble) {
      throw new IllegalArgumentException("There must be a marble between the 'from' and 'to' "
          + "positions.");
    }
    if (getSlotAt(fromRow, fromCol) != SlotState.Marble) {
      throw new IllegalArgumentException("There must be a marble in the 'from' position.");
    }
    if (getSlotAt(toRow, toCol) != SlotState.Empty) {
      throw new IllegalArgumentException("The 'to' position must be an empty position.");
    }
    this.board[fromRow][fromCol] = SlotState.Empty;
    this.board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
    this.board[toRow][toCol] = SlotState.Marble;
  }

  /**
   * Determines and returns if the game is over or not. A game is over if no more moves can be made.
   * Whether another move can be made depends on the specific implementation.
   *
   * @return true if the game is over, false otherwise
   */
  public boolean isGameOver() {
    boolean result = true;
    boolean finished = false;
    if (getScore() != 0) {
      for (int row = 0; row < this.board.length; row++) {
        for (int cell = 0; cell < this.board.length; cell++) {
          if (this.board[row][cell] == SlotState.Marble) {
            if (checkNeighbors(row, cell)) {
              result = false;
              finished = true;
              break;
            }
          }
        }
        if (finished) {
          break;
        }
      }
    }
    return result;
  }

  /**
   * Checks if there are any open moves for a single marble on the board. The conditions for an open
   * move are dependent on certain implementations of the game/model. PRECONDITION: The (row, col)
   * value passed in contains a marble and is not invalid.
   *
   * @param row  represents the given row of the slot that will check if any moves are valid
   * @param cell represents the given column of the slot that will check if any moves are valid
   * @return whether this slot has any valid moves that the player can make
   */
  protected abstract boolean checkNeighbors(int row, int cell);

  /**
   * Checks whether the given slot (row, col) is within bounds of the board.
   *
   * @param row represents the row of the given slot you are checking if within bounds.
   * @param col represents the column of the given slot you are checking if within bounds.
   * @return whether the given slot (row, col) is within the bounds of the board.
   */
  protected boolean validDims(int row, int col) {
    return (row >= 0 && col >= 0 && row < this.getBoardSize() && col < this.getBoardSize());
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board.
   *
   * @return the size as an integer
   */
  public int getBoardSize() {
    return this.board.length;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond the dimensions of the
   *                                  board
   */
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Coordinates cannot be negative.");
    }
    if (row >= getBoardSize() || col >= getBoardSize()) {
      throw new IllegalArgumentException("The inputted dimension is beyond the dimensions of the"
          + "board.");
    }
    return this.board[row][col];
  }

  /**
   * Return the number of marbles currently on the board (score).
   *
   * @return the number of marbles currently on the board
   */
  public int getScore() {
    int count = 0;
    for (SlotState[] row : this.board) {
      for (SlotState cell : row) {
        if (cell == SlotState.Marble) {
          count++;
        }
      }
    }
    return count;
  }

}
