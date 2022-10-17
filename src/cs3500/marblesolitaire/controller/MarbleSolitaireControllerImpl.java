package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Specific implementation of a {@code MarbleSolitaireController}. This class offers the methods and
 * logic necessary to play a full game of {@code MarbleSolitaire}. One object of this class
 * represents one controller for the marble solitaire game.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable input;

  /**
   * Constructs a controller that takes inputs, transmits outputs, and interacts with the model of
   * the game to create a working game of {@code MarbleSolitaire}.
   *
   * @param model The specified model to be used to control the game state
   * @param view  The specified view to output the game to
   * @param input The specified {@code Readable} object to take inputs from
   * @throws IllegalArgumentException if the model, view, or input is null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The provided model object cannot be null.");
    }
    if (view == null) {
      throw new IllegalArgumentException("The provided view object cannot be null.");
    }
    if (input == null) {
      throw new IllegalArgumentException("The provided Readable object cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner s = new Scanner(this.input);
    boolean quit = false;
    boolean waitingForInput;
    int fromRow;
    int fromCol;
    int toRow;
    int toCol;

    while (!this.model.isGameOver() && !quit) {
      waitingForInput = true;
      //step 1 -- render game board
      writeBoard();

      // step 2 -- transmit score
      writeMessage("Score: " + this.model.getScore());

      //step 3 -- obtain user input
      while (waitingForInput) {
        waitingForInput = false;
        writeMessage("Enter the positions for the move to be made: ");
        try {
          fromRow = getNextInt(s);
          fromCol = getNextInt(s);
          toRow = getNextInt(s);
          toCol = getNextInt(s);
        } catch (IllegalArgumentException e) {
          quit = true;
          break;
        }

        //step 4 send to model
        try {
          this.model.move(fromRow - 1, fromCol - 1, toRow - 1, toCol - 1);
        } catch (IllegalArgumentException e) {
          writeMessage("Invalid move. Play again.");
          waitingForInput = true;
        }
      }

    }

    // step 5.1 deal with quit input
    if (quit) {
      quitSequence();
      return;
    }

    //step 5.2 figure if game is over
    gameOverSequence();
    return;

  }

  /**
   * Writes the game-over text sequence to the Appendable object.
   */
  private void gameOverSequence() {
    writeMessage("Game over!");
    writeBoard();
    writeMessage("Score: " + this.model.getScore());
  }

  /**
   * Writes the quit text sequence to the Appendable object.
   */
  private void quitSequence() {
    writeMessage("Game quit!");
    writeMessage("State of game when quit:");
    writeBoard();
    writeMessage("Score: " + this.model.getScore());
  }

  /**
   * Attempts to get the next positive Integer from the {@code Scanner} object.
   *
   * @param s represents the Scanner input object to read from
   * @return the next positive Integer from the Scanner input
   * @throws IllegalArgumentException if the game is quit
   * @throws IllegalStateException    if there are no more input to read
   */
  private int getNextInt(Scanner s) throws IllegalArgumentException, IllegalStateException {
    if (!s.hasNext()) {
      throw new IllegalStateException("Error reading input.");
    }
    int i = 0;
    int inputInteger = 0;
    while (i == 0) {
      i = 1;
      String input = s.next();

      //check if game quit
      if (input.equalsIgnoreCase("q")) {
        throw new IllegalArgumentException("Game quit.");
      }
      // try to parse, if not able, try again
      try {
        inputInteger = Integer.parseInt(input);
        if (inputInteger < 1) {
          throw new IllegalArgumentException("OOB input.");
        }
      } catch (IllegalArgumentException e) {
        writeMessage("Invalid input, try again.");
        i = 0;
      }
    }
    return inputInteger;
  }

  /**
   * Appends the given message to the {@code Appendable} view object.
   *
   * @param message the message to write to the output
   * @throws IllegalStateException if there is an error writing output to the {@code Appendable}
   *                               object
   */
  private void writeMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  /**
   * Appends the state of the board to the {@code Appendable} view object.
   *
   * @throws IllegalStateException if there is an error writing output to the {@code Appendable} *
   *                               object
   */
  private void writeBoard() throws IllegalStateException {
    try {
      this.view.renderBoard();
      this.view.renderMessage("\n");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

}
