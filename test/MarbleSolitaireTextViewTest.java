import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class tests the constructors, methods, and logic of the {@code MarbleSolitaireTextView}
 * class and object creation.
 */
public class MarbleSolitaireTextViewTest {

  MarbleSolitaireModelState modelS1;
  EnglishSolitaireModel model2;
  MarbleSolitaireTextView text1;
  Appendable output;

  @Before
  public void init() {
    model2 = new EnglishSolitaireModel();
  }

  // constructor exceptions
  @Test(expected = IllegalArgumentException.class)
  public void testTextViewNullException() {
    MarbleSolitaireTextView text2 = new MarbleSolitaireTextView(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTextViewNullAppendableException() {
    MarbleSolitaireTextView text2 = new MarbleSolitaireTextView(model2, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTextViewNullAppendableException2() {
    MarbleSolitaireTextView text2 = new MarbleSolitaireTextView(new EuropeanSolitaireModel(), null);
  }

  @Test
  public void testToString() {
    modelS1 = new EnglishSolitaireModelState(1);
    text1 = new MarbleSolitaireTextView(this.modelS1);
    String ans = "  O\n" +
        "O _ O\n" +
        "  O";
    assertEquals(ans, this.text1.toString());

    modelS1 = new EnglishSolitaireModelState(3);
    text1 = new MarbleSolitaireTextView(this.modelS1);
    ans = "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O";
    assertEquals(ans, this.text1.toString());

    modelS1 = new EnglishSolitaireModelState(3, 0, 3);
    text1 = new MarbleSolitaireTextView(modelS1);
    ans = "    O _ O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O";
    assertEquals(ans, this.text1.toString());

    modelS1 = new EnglishSolitaireModelState(3, 2, 6);
    text1 = new MarbleSolitaireTextView(modelS1);
    ans = "    O O O\n" +
        "    O O O\n" +
        "O O O O O O _\n" +
        "O O O O O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O";
    assertEquals(ans, this.text1.toString());

    modelS1 = new EnglishSolitaireModelState(5, 0, 4);
    text1 = new MarbleSolitaireTextView(modelS1);
    ans = "        _ O O O O\n" +
        "        O O O O O\n" +
        "        O O O O O\n" +
        "        O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "        O O O O O\n" +
        "        O O O O O\n" +
        "        O O O O O\n" +
        "        O O O O O";
    assertEquals(ans, this.text1.toString());

    SlotState[][] test = new SlotState[5][5];
    for (int i = 0; i < test.length; i++) {
      for (int j = 0; j < test.length; j++) {
        test[i][j] = SlotState.Empty;
      }
    }

    modelS1 = new EnglishSolitaireModelState(test);
    text1 = new MarbleSolitaireTextView(modelS1);
    ans = "_ _ _ _ _\n" +
        "_ _ _ _ _\n" +
        "_ _ _ _ _\n" +
        "_ _ _ _ _\n" +
        "_ _ _ _ _";
    assertEquals(ans, this.text1.toString());
  }

  @Test
  public void testToStringAfterMove() {
    text1 = new MarbleSolitaireTextView(model2);
    String ans = "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O";
    assertEquals(ans, text1.toString());

    model2.move(3, 1, 3, 3);

    ans = "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O _ _ O O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O";
    assertEquals(ans, text1.toString());
  }

  @Test
  public void testRenderBoard() {
    output = new StringBuilder();
    text1 = new MarbleSolitaireTextView(model2, output);
    String ans = "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O";
    try {
      text1.renderBoard();
    } catch (IOException e) {
      fail("There was an error transmitting output.");
    }
    assertEquals(ans, output.toString());

    model2.move(3, 1, 3, 3);
    try {
      text1.renderBoard();
    } catch (IOException e) {
      fail("There was an error transmitting output.");
    }

    String ans1 = "    O O O\n" +
        "    O O O\n" +
        "O O O O O O O\n" +
        "O _ _ O O O O\n" +
        "O O O O O O O\n" +
        "    O O O\n" +
        "    O O O";
    assertEquals(ans1, output.toString().split(ans)[1]);


  }

  @Test(expected = IOException.class)
  public void testIOExceptionRenderBoard() throws IOException {
    output = new CorruptAppendable();
    text1 = new MarbleSolitaireTextView(model2, output);
    text1.renderBoard();
  }

  @Test
  public void testRenderMessage() {
    output = new StringBuilder();
    text1 = new MarbleSolitaireTextView(model2, output);

    try {
      text1.renderMessage("Hello ");
    } catch (IOException e) {
      fail("There was an error transmitting output.");
    }
    assertEquals("Hello ", output.toString());
    try {
      text1.renderMessage("world!");
    } catch (IOException e) {
      fail("There was an error transmitting output.");
    }
    assertEquals("Hello world!", output.toString());

  }

  @Test(expected = IOException.class)
  public void testIOExceptionRenderMessage() throws IOException {
    output = new CorruptAppendable();
    text1 = new MarbleSolitaireTextView(model2, output);
    text1.renderMessage("This is a pretty good input!" + model2.getScore());
  }

  @Test
  public void testEuropeanToString() {
    text1 = new MarbleSolitaireTextView(new EuropeanSolitaireModel());
    String ans = "    O O O\n" +
        "  O O O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "  O O O O O\n" +
        "    O O O";
    assertEquals(ans, text1.toString());
    text1 = new MarbleSolitaireTextView(new EuropeanSolitaireModel(5, 0, 5));
    //                :O
    ans = "        O _ O O O\n" +
        "      O O O O O O O\n" +
        "    O O O O O O O O O\n" +
        "  O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "O O O O O O O O O O O O O\n" +
        "  O O O O O O O O O O O\n" +
        "    O O O O O O O O O\n" +
        "      O O O O O O O\n" +
        "        O O O O O";
    assertEquals(ans, text1.toString());
  }

  @Test
  public void testEuropeanToStringAfterMove() {
    MarbleSolitaireModel model3 = new EuropeanSolitaireModel();
    text1 = new MarbleSolitaireTextView(model3);
    String ans = "    O O O\n" +
        "  O O O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "  O O O O O\n" +
        "    O O O";
    assertEquals(ans, text1.toString());
    model3.move(3, 1, 3, 3);
    ans = "    O O O\n" +
        "  O O O O O\n" +
        "O O O O O O O\n" +
        "O _ _ O O O O\n" +
        "O O O O O O O\n" +
        "  O O O O O\n" +
        "    O O O";
    assertEquals(ans, text1.toString());
  }

  @Test
  public void testRenderingEuropeanBoard() {
    output = new StringBuilder();
    text1 = new MarbleSolitaireTextView(new EuropeanSolitaireModel(), output);
    try {
      text1.renderBoard();
    } catch (IOException e) {
      fail(e.getMessage());
    }
    String ans = "    O O O\n" +
        "  O O O O O\n" +
        "O O O O O O O\n" +
        "O O O _ O O O\n" +
        "O O O O O O O\n" +
        "  O O O O O\n" +
        "    O O O";
    assertEquals(ans, output.toString());
  }
}
