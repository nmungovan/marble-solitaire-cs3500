import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Mock model object used to confirm the inputs from the controller.
 */

public class ConfirmInputsModel implements MarbleSolitaireModel {
  final StringBuilder log;

  /**
   * Creates a {@code ConfirmInputsModel} that helps validate the data passed in from the
   * controller.
   *
   * @param log holds the values obtained from the controller
   */
  public ConfirmInputsModel(StringBuilder log) {
    this.log = log;
  }

  /**
   * Logs the values inputted from the controller.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException Never, this is a mock model.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    log.append("New Log Entry:" + fromRow + ", " + fromCol + ", " + toRow + ", " + toCol);
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  /**
   * Logs the values inputted from the controller.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return Invalid {@code SlotState}.
   * @throws IllegalArgumentException Never, this is a mock.
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    log.append("New Log Entry:" + row + " ," + col);
    return SlotState.Invalid;
  }

  @Override
  public int getScore() {
    return 0;
  }
}
