import java.io.IOException;

import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Mock view object used to confirm the outputs from the controller.
 */
public class ConfirmOutputsView implements MarbleSolitaireView {
  final StringBuilder log;

  /**
   * Creates a {@code ConfirmOutputsView} that helps validate the data passed in from the
   * controller.
   *
   * @param log holds the values obtained from the controller
   */
  public ConfirmOutputsView(StringBuilder log) {
    this.log = log;
  }

  /**
   * Logs the message to the log that the board was attempted to be rendered when called.
   *
   * @throws IOException Never, this is a mock model.
   */
  @Override
  public void renderBoard() throws IOException {
    log.append("New Log Entry:Board rendering attempted.");
  }

  /**
   * Logs the message to the log that a message was attempted to be rendered when called.
   *
   * @param message the message to be transmitted
   * @throws IOException Never, this is a mock model.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    log.append("New Log Entry:Message: \"" + message + "\" rendering attempted.");
  }
}
