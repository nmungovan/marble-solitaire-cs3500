import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the constructors, methods, and logic of the {@code EnglishSolitaireModelState}
 * class and object creation.
 */

public class EnglishSolitaireModelStateTest {
  private EnglishSolitaireModelState exceptionModel;
  private EnglishSolitaireModelState randState;
  private EnglishSolitaireModelState model1;
  private EnglishSolitaireModelState model3;
  private EnglishSolitaireModelState model3_1;
  private EnglishSolitaireModelState model7;
  private SlotState[][] testBoard;
  private int randStateLength;


  @Before
  public void init() {
    randStateLength = new Random().nextInt(1000) * 2 + 1;
    model1 = new EnglishSolitaireModelState(1);
    model3 = new EnglishSolitaireModelState(3);
    model3_1 = new EnglishSolitaireModelState(3, 0, 3);
    model7 = new EnglishSolitaireModelState(7, 0, 8);
    randState = new EnglishSolitaireModelState(randStateLength);
  }

  //test constructor exceptions
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNegativeArmLength() {
    exceptionModel = new EnglishSolitaireModelState(-2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNegativeArmLength2() {
    exceptionModel = new EnglishSolitaireModelState(-4, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionEvenArmLength() {
    exceptionModel = new EnglishSolitaireModelState(6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNegativeCoordinates() {
    exceptionModel = new EnglishSolitaireModelState(3, -4, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNegativeCoordinates2() {
    exceptionModel = new EnglishSolitaireModelState(3, -4, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNegativeCoordinates3() {
    exceptionModel = new EnglishSolitaireModelState(5, 0, -4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionOOBCoordinates() {
    exceptionModel = new EnglishSolitaireModelState(3, 7, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionOOBCoordinates2() {
    exceptionModel = new EnglishSolitaireModelState(5, 0, 13);
  }

  @Test
  public void workingConstructors() {
    model3 = new EnglishSolitaireModelState(3);
    assertEquals(32, this.model3.getScore());
    assertEquals(SlotState.Empty, this.model3.getSlotAt(3, 3));
    assertEquals(7, this.model3.getBoardSize());

    model7 = new EnglishSolitaireModelState(7, 0, 8);
    assertEquals(216, this.model7.getScore());
    assertEquals(SlotState.Empty, this.model7.getSlotAt(0, 8));
    assertEquals(19, this.model7.getBoardSize());
  }

  @Test
  public void getBoardSize() {
    for (int i = 3; i < 501; i = i + 2) {
      assertEquals(3L * i - 2, new EnglishSolitaireModelState(i).getBoardSize());
    }
    assertEquals(3, model1.getBoardSize());
    assertEquals(7, model3_1.getBoardSize());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtExceptionNegativeDims() {
    this.model1.getSlotAt(-3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtExceptionNegativeDims2() {
    this.model3.getSlotAt(3, -6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtExceptionOOBDims() {
    this.model3_1.getSlotAt(9, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtExceptionOOBDims2() {
    this.model7.getSlotAt(3, 22);
  }

  @Test
  public void testGetSlotAt() {
    for (int i = 0; i < randState.getBoardSize(); i++) {
      for (int j = 0; j < randState.getBoardSize(); j++) {
        if (i < (randState.getBoardSize() / 2 - (randStateLength / 2))
            || i > (randState.getBoardSize() / 2 + (randStateLength / 2))) {
          if (j < (randState.getBoardSize() / 2 - (randStateLength / 2))
              || j > (randState.getBoardSize() / 2 + (randStateLength / 2))) {
            assertEquals(SlotState.Invalid, randState.getSlotAt(i, j));
          } else {
            assertEquals(SlotState.Marble, randState.getSlotAt(i, j));
          }
        } else if (i == randState.getBoardSize() / 2 && j == randState.getBoardSize() / 2) {
          assertEquals(SlotState.Empty, randState.getSlotAt(i, j));
        } else {
          assertEquals(SlotState.Marble, randState.getSlotAt(i, j));
        }
      }
    }
  }

  @Test
  public void testGetSlotAt2() {
    for (int i = 0; i < this.model7.getBoardSize(); i++) {
      for (int j = 0; j < this.model7.getBoardSize(); j++) {
        if (i == 0 && j == 8) {
          assertEquals(SlotState.Empty, this.model7.getSlotAt(i, j));
        } else if (i < 6 || i > 12) {
          if (j < 6 || j > 12) {
            assertEquals(SlotState.Invalid, this.model7.getSlotAt(i, j));
          } else {
            assertEquals(SlotState.Marble, this.model7.getSlotAt(i, j));
          }
        } else {
          assertEquals(SlotState.Marble, this.model7.getSlotAt(i, j));
        }
      }
    }
  }

  @Test
  public void getScore() {
    assertEquals(4, this.model1.getScore());
    assertEquals(32, this.model3.getScore());
    assertEquals(32, this.model3_1.getScore());
    assertEquals(216, this.model7.getScore());
  }

}