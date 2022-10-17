import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * This class tests the constructors, methods, and logic of the {@code MarbleSolitaireController}
 * class and object/model creation.
 */
public class MarbleSolitaireControllerTest {

  private Readable input;
  private Appendable output;
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private MarbleSolitaireController ctrl;

  @Before
  public void init() {
    output = new StringBuilder();
    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model, output);
  }

  // asserts that the controller is instantiated successfully, reads input, writes output, and
  // interacts successfully with the view and model passed to it.
  @Test
  public void testConstructorWorks() {
    input = new StringReader("4 6 4 4 q");
    ctrl = new MarbleSolitaireControllerImpl(model, view, input);
    ctrl.playGame();
    assertFalse(input.toString().isEmpty());
    assertFalse(output.toString().isEmpty());
    String viewToString = "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O O _ _ O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O";
    assertEquals(viewToString, view.toString());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(3, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionNullModel() {
    input = new StringReader("");
    ctrl = new MarbleSolitaireControllerImpl(null, this.view, this.input);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionNullView() {
    input = new StringReader("");
    ctrl = new MarbleSolitaireControllerImpl(this.model, null, input);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptionNullInputStream() {
    ctrl = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);
  }

  @Test
  public void testPlayGame1WithGameOver() {
    input = new StringReader("4 6 4 4 6 5 4 5 5 7 5 5 5 4 5 6 5 2 5 4 7 3 5 3 4 3 6 3 7 5 7 3 7 "
        + "3 5 3 3 5 5 5 1 5 3 5 2 3 4 3 4 3 6 3 6 3 6 5 6 5 4 5 4 5 2 5 3 7 5 7 5 7 5 5 5 5 5 3 "
        + "3 1 3 3 3 4 3 2 5 1 3 1 3 1 3 3 1 3 1 5 1 5 3 5 3 6 3 4 3 4 3 2 3 2 5 2 5 2 5 4 5 4 3 4 "
        + "2 4 4 4");
    ctrl = new MarbleSolitaireControllerImpl(model, view, input);
    ctrl.playGame();
    String[] outputArray = output.toString().split("Enter the positions for the move to be made:");
    String initial = "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 32\n";
    String move1 = " \n    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O O _ _ O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 31\n";
    String gameOver = " \nGame over!\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "_ _ _ _ _ _ _\n" +
        "_ _ _ O _ _ _\n" +
        "_ _ _ _ _ _ _\n" +
        "    _ _ _\n" +
        "    _ _ _\n" +
        "Score: 1\n";
    assertEquals(initial, outputArray[0]);
    assertEquals(move1, outputArray[1]);
    assertEquals(gameOver, outputArray[outputArray.length - 1]);
  }

  @Test
  public void testPlayGame1WithGameQuit() {
    input = new StringReader("4 2 4 4 q");
    ctrl = new MarbleSolitaireControllerImpl(model, view, input);
    ctrl.playGame();
    String[] outputArray = output.toString().split("Enter the positions for the move to be made:");
    String initial = "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 32\n";
    String move1 = " \n    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O _ _ O O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 31\n";
    String gameQuit = " \n" +
        "Game quit!\n" +
        "State of game when quit:\n" +
        "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O _ _ O O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 31\n";
    assertEquals(initial, outputArray[0]);
    assertEquals(move1, outputArray[1]);
    assertEquals(gameQuit, outputArray[outputArray.length - 1]);
  }

  @Test
  public void testEuroGame1WithGameQuit() {
    input = new StringReader("4 2 4 4 q");
    this.model = new EuropeanSolitaireModel();
    this.view = new MarbleSolitaireTextView(model, output);
    ctrl = new MarbleSolitaireControllerImpl(model, view, input);
    ctrl.playGame();
    String[] outputArray = output.toString().split("Enter the positions for the move to be made:");
    String initial = "    O O O\n" +
        "  O O O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "  O O O O O\n" +
        "    O O O\n" +
        "Score: 36\n";
    String move1 = " \n" +
        "    O O O\n" +
        "  O O O O O\n" +
        "O O O O O O O\n" +
        "O _ _ O O O O\n" +
        "O O O O O O O\n" +
        "  O O O O O\n" +
        "    O O O\n" +
        "Score: 35\n";
    String gameQuit = " \n" +
        "Game quit!\n" +
        "State of game when quit:\n" +
        "    O O O\n" +
        "  O O O O O\n" +
        "O O O O O O O\n" +
        "O _ _ O O O O\n" +
        "O O O O O O O\n" +
        "  O O O O O\n" +
        "    O O O\n" +
        "Score: 35\n";
    assertEquals(initial, outputArray[0]);
    assertEquals(move1, outputArray[1]);
    assertEquals(gameQuit, outputArray[outputArray.length - 1]);
  }

  @Test
  public void testGameQuitAllPositions()  {
    model = new TriangleSolitaireModel();
    input = new StringReader("3 q");
    ctrl = new MarbleSolitaireControllerImpl(model, new TriangleSolitaireTextView(model, output),
        input);
    ctrl.playGame();
    String[] outputArray = output.toString().split("Enter the positions for the move to be made:");
    String initial = "    _\n" +
        "   O O\n" +
        "  O O O\n" +
        " O O O O\n" +
        "O O O O O\n" +
        "Score: 14\n";
    String gameQuit = " \n" +
        "Game quit!\n" +
        "State of game when quit:\n" +
        "    _\n" +
        "   O O\n" +
        "  O O O\n" +
        " O O O O\n" +
        "O O O O O\n" +
        "Score: 14\n";
    assertEquals(initial, outputArray[0]);
    assertEquals(gameQuit, outputArray[1]);
    input = new StringReader("3 1 q");
    ctrl = new MarbleSolitaireControllerImpl(model, new TriangleSolitaireTextView(model, output),
        input);
    ctrl.playGame();
    assertEquals(initial, outputArray[0]);
    assertEquals(gameQuit, outputArray[1]);
    input = new StringReader("3 1 1 q");
    ctrl = new MarbleSolitaireControllerImpl(model, new TriangleSolitaireTextView(model, output),
        input);
    ctrl.playGame();
    assertEquals(initial, outputArray[0]);
    assertEquals(gameQuit, outputArray[1]);
  }

  @Test
  public void testPlayGame1InvalidInput() {
    input = new StringReader("4 6 4 4 6 0 ghhg 5 4 5 q");
    ctrl = new MarbleSolitaireControllerImpl(model, view, input);
    ctrl.playGame();
    String[] outputArray = output.toString().split("Enter the positions for the move to be made:");
    String move1 = " \n    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O O _ _ O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O\n" +
        "Score: 31\n";
    String move2 = " \n" +
        "Invalid input, try again.\n" +
        "Invalid input, try again.\n" +
        "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O O O _ O\n" +
        "O O O O _ O O\n" +
        "    O O _\n" +
        "    O O O\n" +
        "Score: 30\n";
    assertEquals(move1, outputArray[1]);
    assertEquals(move2, outputArray[2]);
  }

  @Test
  public void testPlayGame1InvalidMoveAndInputs() {
    input = new StringReader("4 6 4 4 6 0 ghhg 5 4 6 6 5 4 5 q");
    ctrl = new MarbleSolitaireControllerImpl(model, view, input);
    ctrl.playGame();
    String[] outputArray = output.toString().split("Enter the positions for the move to be made:");
    String move2 = " \n" +
        "Invalid input, try again.\n" +
        "Invalid input, try again.\n" +
        "Invalid move. Play again.\n";
    String move3 = " \n" +
        "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O O O _ O\n" +
        "O O O O _ O O\n" +
        "    O O _\n" +
        "    O O O\n" +
        "Score: 30\n";
    assertEquals(move2, outputArray[2]);
    assertEquals(move3, outputArray[3]);
  }


  @Test(expected = IllegalStateException.class)
  public void testPlayGameRunOutOfInput() {
    input = new StringReader("3 4 3");
    ctrl = new MarbleSolitaireControllerImpl(this.model, this.view, this.input);
    ctrl.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayGameInputException() {
    ctrl = new MarbleSolitaireControllerImpl(this.model, this.view, new CorruptReadable());
    ctrl.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayGameOutputException() {
    view = new MarbleSolitaireTextView(this.model, new CorruptAppendable());
    ctrl = new MarbleSolitaireControllerImpl(this.model, this.view, new StringReader("4 4 4 6 q"));
    ctrl.playGame();
  }

  //the skipped String[] output values were newlines
  @Test
  public void testControllerInputOutput() {
    StringBuilder log = new StringBuilder();
    view = new ConfirmOutputsView(log);
    model = new ConfirmInputsModel(log);
    input = new StringReader("4 6 4 4 6 0 ghhg 5 4 6 q");
    ctrl = new MarbleSolitaireControllerImpl(model, view, input);
    ctrl.playGame();
    String[] output = log.toString().split("New Log Entry:");
    String newLine = "Message: \"\n\" rendering attempted.";
    assertEquals("Board rendering attempted.", output[1]);
    assertEquals(newLine, output[2]);
    assertEquals("Message: \"Score: 0\n\" rendering attempted.", output[3]);
    assertEquals("Message: \"Enter the positions for the move to be made: \n\" rendering "
        + "attempted.", output[4]);
    assertEquals("3, 5, 3, 3", output[5]);
    assertEquals("Board rendering attempted.", output[6]);
    assertEquals("Message: \"Score: 0\n\" rendering attempted.", output[8]);
    assertEquals("Message: \"Enter the positions for the move to be made: \n\" rendering "
        + "attempted.", output[9]);
    assertEquals("Message: \"Invalid input, try again.\n\" rendering attempted.",
        output[10]);
    assertEquals(output[10], output[11]);
    assertEquals("5, 4, 3, 5", output[12]);
    assertEquals("Board rendering attempted.", output[13]);
    assertEquals("Message: \"Score: 0\n\" rendering attempted.", output[15]);
    assertEquals("Message: \"Enter the positions for the move to be made: \n\" rendering "
        + "attempted.", output[16]);
    assertEquals("Message: \"Game quit!\n\" rendering attempted.", output[17]);
    assertEquals("Message: \"State of game when quit:\n\" rendering attempted.", output[18]);
    assertEquals("Board rendering attempted.", output[19]);
    assertEquals("Message: \"Score: 0\n\" rendering attempted.", output[21]);
  }

}
