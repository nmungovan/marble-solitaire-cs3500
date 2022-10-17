import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class tests the constructors, methods, and logic of the {@code TriangleSolitaireTextView}
 * class and object creation.
 */
public class TriangleSolitaireTextViewTest {

  MarbleSolitaireView view;
  Appendable out;
  MarbleSolitaireModel model;

  @Before
  public void init() {
    out = new StringBuilder();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    view = new TriangleSolitaireTextView(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullOutput() {
    view = new TriangleSolitaireTextView(new TriangleSolitaireModel(), null);
  }

  @Test
  public void testConstructor1Works() {
    model = new TriangleSolitaireModel();
    view = new TriangleSolitaireTextView(model, out);
    String ans = "    _\n" +
        "   O O\n" +
        "  O O O\n" +
        " O O O O\n" +
        "O O O O O";
    assertEquals(ans, view.toString());
    try {
      view.renderBoard();
    } catch (IOException e) {
      fail(e.getMessage());
    }
    assertEquals(ans, out.toString());
  }

  @Test
  public void testConstructor2Works() {
    model = new TriangleSolitaireModel(3);
    view = new TriangleSolitaireTextView(model);
    String ans = "  _\n" +
        " O O\n" +
        "O O O";
    assertEquals(ans, view.toString());
  }

  @Test
  public void testToString() {
    model = new TriangleSolitaireModel(4, 3);
    view = new TriangleSolitaireTextView(model);
    String ans = "    O\n" +
        "   O O\n" +
        "  O O O\n" +
        " O O O O\n" +
        "O O O _ O";
    assertEquals(ans, view.toString());
    model = new TriangleSolitaireModel(7, 4, 4);
    view = new TriangleSolitaireTextView(model);
    ans = "      O\n" +
        "     O O\n" +
        "    O O O\n" +
        "   O O O O\n" +
        "  O O O O _\n" +
        " O O O O O O\n" +
        "O O O O O O O";
    assertEquals(ans, view.toString());
  }

  @Test
  public void testToStringAfterMove() {
    model = new TriangleSolitaireModel(7, 2, 1);
    view = new TriangleSolitaireTextView(model);
    String ans = "      O\n" +
        "     O O\n" +
        "    O _ O\n" +
        "   O O O O\n" +
        "  O O O O O\n" +
        " O O O O O O\n" +
        "O O O O O O O";
    assertEquals(ans, view.toString());
    model.move(4, 1, 2, 1);
    ans = "      O\n" +
        "     O O\n" +
        "    O O O\n" +
        "   O _ O O\n" +
        "  O _ O O O\n" +
        " O O O O O O\n" +
        "O O O O O O O";
    assertEquals(ans, view.toString());
  }

  @Test
  public void testRenderTriangleBoard() {
    model = new TriangleSolitaireModel(3, 1, 0);
    view = new TriangleSolitaireTextView(model, out);
    try {
      view.renderBoard();
    } catch (IOException e) {
      fail(e.getMessage());
    }
    String ans = "  O\n" +
        " _ O\n" +
        "O O O";
    assertEquals(ans, out.toString());
  }

  @Test(expected = IOException.class)
  public void testCorruptAppendable() throws IOException {
    out = new CorruptAppendable();
    model = new TriangleSolitaireModel();
    view = new TriangleSolitaireTextView(model, out);
    view.renderBoard();
  }
}
