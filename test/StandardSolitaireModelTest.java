import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the constructors, methods, and logic of the {@code StandardSolitaireModel} class
 * and object/model creation. Tests methods of the two implementations that have not already been
 * tested (EuropeanSolitaireModel, TriangleSolitaireModel)
 */
public class StandardSolitaireModelTest {
  MarbleSolitaireModel euro;
  MarbleSolitaireModel tri;

  @Before
  public void setUp() {
    euro = new EuropeanSolitaireModel();
    tri = new TriangleSolitaireModel();
  }

  @Test
  public void isGameOver() {
    assertFalse(euro.isGameOver());
    assertFalse(tri.isGameOver());
    MarbleSolitaireModel tri1 = new TriangleSolitaireModel(2);
    assertTrue(tri1.isGameOver());
    MarbleSolitaireModel euro1 = new EuropeanSolitaireModel(1);
    assertTrue(euro1.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    int ans;
    for (int i = 1; i < 100; i++) {
      tri = new TriangleSolitaireModel(i);
      assertEquals(i, tri.getBoardSize());
    }

    for (int i = 3; i < 100; i = i + 2) {
      ans = i * 3 - 2;
      euro = new EuropeanSolitaireModel(i);
      assertEquals(ans, euro.getBoardSize());
    }
  }

  @Test
  public void getSlotAt() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j <= i; j++) {
        if (i == 0 && j == 0) {
          assertEquals(SlotState.Empty, tri.getSlotAt(i, j));
        } else {
          assertEquals(SlotState.Marble, tri.getSlotAt(i, j));
        }
      }
    }

    int length = euro.getBoardSize();
    int lowBound = (length / 2 - ((length + 2) / 6));
    int highBound = (length / 2 + ((length + 2) / 6));
    for (int row = 0; row < length; row++) {
      for (int cell = 0; cell < length; cell++) {
        if (row == 3 && cell == 3) {
          assertEquals(SlotState.Empty, euro.getSlotAt(row, cell));
        } else if (cell < lowBound || cell > highBound) {
          assertEquals(SlotState.Invalid, euro.getSlotAt(row, cell));
        } else {
          assertEquals(SlotState.Marble, euro.getSlotAt(row, cell));
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

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtInvalidDimsEuro1() {
    euro.getSlotAt(-3, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtInvalidDimsEuro2() {
    euro.getSlotAt(9, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtInvalidDimsTri1() {
    tri.getSlotAt(-3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtInvalidDimsTri2() {
    tri.getSlotAt(7, 3);
  }

  @Test
  public void getScore() {
    assertEquals(36, euro.getScore());
    euro.move(3, 1, 3, 3);
    assertEquals(35, euro.getScore());
    assertEquals(14, tri.getScore());
    tri.move(2, 0, 0, 0);
    assertEquals(13, tri.getScore());
  }

}