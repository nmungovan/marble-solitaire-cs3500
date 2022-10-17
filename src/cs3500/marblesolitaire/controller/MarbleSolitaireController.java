package cs3500.marblesolitaire.controller;

/**
 * This interface represents the operations offered by the marble solitaire controller. One object
 * of the controller represents one game of marble solitaire.
 */
public interface MarbleSolitaireController {

  /**
   * Starts a new game of Marble Solitaire.
   *
   * @throws IllegalStateException if the controller is unable to read input or write output.
   */
  void playGame() throws IllegalStateException;

}
