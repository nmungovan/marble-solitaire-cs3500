import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This class tests the constructors, methods, and logic of the {@code EnglishSolitaireModel } class
 * and object/model creation.
 */

public class EnglishSolitaireModelTest {
  private EnglishSolitaireModel model1;
  private EnglishSolitaireModel model2;
  private EnglishSolitaireModel model2_1;
  private EnglishSolitaireModel model3;
  private EnglishSolitaireModel exceptionModel;
  private EnglishSolitaireModel randState;
  private EnglishSolitaireModel testMoveModel;
  private int randStateLength;
  private SlotState[][] testBoard;

  @Before
  public void init() {
    randStateLength = new Random().nextInt(1000) * 2 + 1;
    model1 = new EnglishSolitaireModel(1);
    model2 = new EnglishSolitaireModel();
    model2_1 = new EnglishSolitaireModel(0, 3);
    model3 = new EnglishSolitaireModel(7, 0, 8);
    randState = new EnglishSolitaireModel(randStateLength);
    testBoard = new SlotState[9][9];
    for (SlotState[] row : testBoard) {
      for (SlotState cell : row) {
        cell = SlotState.Empty;
      }
    }
    testMoveModel = new EnglishSolitaireModel(testBoard);
  }

  //constructor exceptions!
  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNegativeArmLength() {
    exceptionModel = new EnglishSolitaireModel(-6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNegativeArmLength2() {
    exceptionModel = new EnglishSolitaireModel(-4, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionEvenArmLength() {
    exceptionModel = new EnglishSolitaireModel(8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNegativeCoordinates() {
    exceptionModel = new EnglishSolitaireModel(-4, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNegativeCoordinates2() {
    exceptionModel = new EnglishSolitaireModel(5, -4, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionNegativeCoordinates3() {
    exceptionModel = new EnglishSolitaireModel(1, 0, -4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionOOBCoordinates() {
    exceptionModel = new EnglishSolitaireModel(7, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExceptionOOBCoordinates2() {
    exceptionModel = new EnglishSolitaireModel(5, 0, 13);
  }

  @Test
  public void workingConstructors() {
    model1 = new EnglishSolitaireModel(1);
    assertEquals(4, this.model1.getScore());
    assertEquals(SlotState.Empty, this.model1.getSlotAt(1, 1));
    assertEquals(3, this.model1.getBoardSize());

    model2 = new EnglishSolitaireModel();
    assertEquals(32, this.model2.getScore());
    assertEquals(SlotState.Empty, this.model2.getSlotAt(3, 3));
    assertEquals(7, this.model2.getBoardSize());

    model2_1 = new EnglishSolitaireModel(0, 3);
    assertEquals(32, this.model2_1.getScore());
    assertEquals(SlotState.Empty, this.model2_1.getSlotAt(0, 3));
    assertEquals(7, this.model2_1.getBoardSize());

    model3 = new EnglishSolitaireModel(7, 0, 6);
    assertEquals(216, this.model3.getScore());
    assertEquals(SlotState.Empty, this.model3.getSlotAt(0, 6));
    assertEquals(19, this.model3.getBoardSize());
  }


  @Test
  public void move() {
    assertEquals(SlotState.Empty, this.model2.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, this.model2.getSlotAt(2, 3));
    assertEquals(SlotState.Marble, this.model2.getSlotAt(1, 3));
    this.model2.move(1, 3, 3, 3);
    assertEquals(SlotState.Marble, this.model2.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, this.model2.getSlotAt(2, 3));
    assertEquals(SlotState.Empty, this.model2.getSlotAt(1, 3));
    this.model2.move(2, 1, 2, 3);
    assertEquals(SlotState.Marble, this.model2.getSlotAt(2, 3));
    assertEquals(SlotState.Empty, this.model2.getSlotAt(2, 2));
    assertEquals(SlotState.Empty, this.model2.getSlotAt(2, 1));

    assertEquals(SlotState.Empty, this.model3.getSlotAt(0, 8));
    assertEquals(SlotState.Marble, this.model3.getSlotAt(0, 9));
    assertEquals(SlotState.Marble, this.model3.getSlotAt(0, 10));
    this.model3.move(0, 10, 0, 8);
    assertEquals(SlotState.Marble, this.model3.getSlotAt(0, 8));
    assertEquals(SlotState.Empty, this.model3.getSlotAt(0, 9));
    assertEquals(SlotState.Empty, this.model3.getSlotAt(0, 10));
    assertEquals(SlotState.Marble, this.model3.getSlotAt(1, 10));
    assertEquals(SlotState.Marble, this.model3.getSlotAt(2, 10));
    this.model3.move(2, 10, 0, 10);
    assertEquals(SlotState.Marble, this.model3.getSlotAt(0, 10));
    assertEquals(SlotState.Empty, this.model3.getSlotAt(1, 10));
    assertEquals(SlotState.Empty, this.model3.getSlotAt(2, 10));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNoFromMarble() {
    this.model2.move(3, 3, 1, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionMarbleToPosition() {
    this.model3.move(0, 4, 0, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionGreaterThanTwoPos() {
    this.model2_1.move(4, 3, 0, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionLessThanTwoPos() {
    this.model2_1.move(1, 3, 0, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionNoMarbleBetween() {
    testBoard[2][3] = SlotState.Marble;
    assertEquals(SlotState.Marble, this.testMoveModel.getSlotAt(2, 3));
    this.testMoveModel.move(2, 3, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidDims() {
    this.model1.move(-1, 0, -1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidDims2() {
    this.model2.move(1, 0, 1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidDims3() {
    this.model2.move(0, 0, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionInvalidDims4() {
    this.model2.move(3, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveExceptionDiagonalMove() {
    this.model2.move(2, 2, 4, 4);
  }

  @Test
  public void isGameOver() {
    assertTrue(this.model1.isGameOver());
    assertFalse(this.model2.isGameOver());
    assertFalse(this.model2_1.isGameOver());
    assertFalse(this.model3.isGameOver());
    assertTrue(this.testMoveModel.isGameOver());
    for (int i = 0; i < testBoard.length; i++) {
      testBoard[4][i] = SlotState.Marble;
    }
    assertEquals(SlotState.Marble, this.testMoveModel.getSlotAt(4, 1));
    assertTrue(this.testMoveModel.isGameOver());
    testBoard[5][0] = SlotState.Marble;
  }

  @Test
  public void getBoardSize() {
    for (int i = 3; i < 501; i = i + 2) {
      assertEquals(3 * i - 2, new EnglishSolitaireModelState(i).getBoardSize());
    }
    assertEquals(7, model2_1.getBoardSize());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtExceptionNegativeDims() {
    this.model1.getSlotAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtExceptionNegativeDims2() {
    this.model2.getSlotAt(3, -4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtExceptionOOBDims() {
    this.model2_1.getSlotAt(7, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtExceptionOOBDims2() {
    this.model2.getSlotAt(3, 16);
  }

  @Test
  public void getSlotAt() {
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
  public void getScore() {
    assertEquals(4, this.model1.getScore());
    assertEquals(32, this.model2.getScore());
    assertEquals(32, this.model2_1.getScore());
    assertEquals(216, this.model3.getScore());
    assertEquals(50600,
        new EnglishSolitaireModel(101, 45, 100).getScore());
  }
}