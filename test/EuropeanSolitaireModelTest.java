import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the constructors, methods, and logic of the {@code EuropeanSolitaireModel} class
 * and object/model creation.
 */
public class EuropeanSolitaireModelTest extends ATestModelClass {

  MarbleSolitaireModel euro1;
  MarbleSolitaireModel euro2;
  MarbleSolitaireModel euro3;
  MarbleSolitaireModel euro4;

  @Before
  public void init() {
    euro1 = new EuropeanSolitaireModel();
    euro2 = new EuropeanSolitaireModel(5);
    euro3 = new EuropeanSolitaireModel(3, 4);
    euro4 = new EuropeanSolitaireModel(5, 3, 4);
  }

  @Test
  public void testConstructor1Works() {
    assertEquals(7, euro1.getBoardSize());
    assertBoard(euro1, 3, 3);
  }

  @Test
  public void testConstructor2Works() {
    assertEquals(13, euro2.getBoardSize());
    assertBoard(euro2, 6, 6);
  }

  @Test
  public void testConstructor3Works() {
    assertEquals(7, euro3.getBoardSize());
    assertBoard(euro3, 3, 4);
  }

  @Test
  public void testConstructor4Works() {
    assertEquals(13, euro4.getBoardSize());
    assertBoard(euro4, 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeNumber() {
    euro1 = new EuropeanSolitaireModel(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorEvenNumber() {
    euro4 = new EuropeanSolitaireModel(4, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorOOBCoordinates() {
    euro2 = new EuropeanSolitaireModel(16, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidCoordinates() {
    euro1 = new EuropeanSolitaireModel(0, 0);
  }

  @Test
  public void testMove() {
    assertMove(euro1, 3, 1, 3, 3);
    assertMove(euro1, 3, 4, 3, 2);
    assertMove(euro1, 1, 3, 3, 3);
    assertMove(euro1, 5, 1, 3, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMove1RowAway() {
    euro1.move(3, 0, 4, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMove3RowAway() {
    euro1.move(2, 1, 5, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveDiagonal() {
    euro1.move(3, 0, 5, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveNoFromMarble() {
    euro1.move(3, 3, 3, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveToPositionNotEmpty() {
    euro1.move(3, 2, 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveNoMarbleBetween() {
    euro1.move(3, 1, 3, 3);
    euro1.move(3, 0, 3, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidDims() {
    euro1.move(0, 0, 2, 0);
  }

  @Override
  void assertBoard(MarbleSolitaireModel model, int eRow, int eCol) {
    int length = model.getBoardSize();
    int lowBound = (length / 2 - ((length + 2) / 6));
    int highBound = (length / 2 + ((length + 2) / 6));
    for (int row = 0; row < length; row++) {
      for (int cell = 0; cell < length; cell++) {
        if (row == eRow && cell == eCol) {
          assertEquals(SlotState.Empty, model.getSlotAt(row, cell));
        } else if (cell < lowBound || cell > highBound) {
          assertEquals(SlotState.Invalid, model.getSlotAt(row, cell));
        } else {
          assertEquals(SlotState.Marble, model.getSlotAt(row, cell));
        }
      }
      if (row < (length / 2 - ((length + 2) / 6))) {
        lowBound--;
        highBound++;
      } else if (row >= (length / 2 + ((length + 2) / 6))) {
        lowBound++;
        highBound--;
      }
    }
  }
}
