package cs3500.marblesolitaire.model.hw02;

/**
 * *OBSOLETE* -- Kept because of tests that check these methods work, but is not used as of
 * Assignment 3.
 * 
 * <p>Represents the state of the {@code EnglishSolitaireModel} which contains the board for the
 * game, and methods/logic in order to follow the rules of English Marble Solitaire and enables the
 * player to play the game. One object of the model state represents one state of English Marble
 * Solitaire.
 */

public class EnglishSolitaireModelState implements MarbleSolitaireModelState {
  /**
   * Represents the 2D-array/board of {@code SlotState} in which the solitaire game is played.
   */
  private final SlotState[][] board;

  /**
   * Creates an {@code EnglishSolitaireModelState}, which holds the board representation. This
   * constructor places the initial empty slot with the given coordinate.
   *
   * @param armLength represents the number of marbles in the top row.
   * @throws IllegalArgumentException if the inputted length is not positive and odd or coordinates
   *                                  are out of bounds.
   */
  public EnglishSolitaireModelState(int armLength, int emptyRow, int emptyCol)
      throws IllegalArgumentException {
    if (armLength < 0 || armLength % 2 == 0) {
      throw new IllegalArgumentException("Length must be a positive, odd number.");
    }
    if (emptyRow < 0 || emptyCol < 0) {
      throw new IllegalArgumentException("Coordinates cannot be negative.");
    }
    int length = armLength + Math.max(2, 2 * (armLength - 1));
    if (emptyRow >= length || emptyCol >= length) {
      throw new IllegalArgumentException("The inputted dimension is beyond the dimensions of the "
          + "board.");
    }
    this.board = createBoard(armLength, emptyRow, emptyCol);
  }

  /**
   * Creates an EnglishSolitaireModelState, which holds the board representation. This constructor
   * places the initial empty slot in the center.
   *
   * @param armLength represents the number of marbles in the top column.
   * @throws IllegalArgumentException if the inputted length is not positive and odd.
   */
  public EnglishSolitaireModelState(int armLength) {
    this(armLength, Math.max(1, (3 * armLength - 2) / 2), Math.max(1, (3 * armLength - 2) / 2));
  }

  /**
   * Constructor **used for testing** in which a board can be passed in.
   *
   * @param board represents the board that will be used instead of creating one.
   */
  public EnglishSolitaireModelState(SlotState[][] board) {
    this.board = board;
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board.
   *
   * @return the size as an integer
   */
  @Override
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
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Coordinates cannot be negative.");
    }
    if (row >= this.getBoardSize() || col >= this.getBoardSize()) {
      throw new IllegalArgumentException("The inputted dimension is beyond the dimensions of the"
          + "board.");
    }
    return this.board[row][col];
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
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

  /**
   * Initializes and creates the board with a valid layout and an empty space at the specified
   * position for a {@code MarbleSolitaireModelState}.
   *
   * @param armLength represents the length of the top/bottom row
   * @param emptyRow  represents the x-coordinate of the empty slot of this board
   * @param emptyCol  represents the y-coordinate of the empty slot of this board
   * @throws IllegalArgumentException if the marble is attempted to be put in a position that is
   *                                  {@code SlotState.Invalid}
   */
  private SlotState[][] createBoard(int armLength, int emptyRow, int emptyCol)
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
