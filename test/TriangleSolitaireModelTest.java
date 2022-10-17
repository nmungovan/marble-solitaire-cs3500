import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the constructors, methods, and logic of the {@code TriangleSolitaireModel} class
 * and object/model creation.
 */
public class TriangleSolitaireModelTest extends ATestModelClass {

  MarbleSolitaireModel tri1;
  MarbleSolitaireModel tri2;
  MarbleSolitaireModel tri3;
  MarbleSolitaireModel tri4;

  @Before
  public void init() {
    tri1 = new TriangleSolitaireModel();
    tri2 = new TriangleSolitaireModel(1);
    tri3 = new TriangleSolitaireModel(3, 2);
    tri4 = new TriangleSolitaireModel(6, 4, 3);
  }

  @Test
  public void testConstructor1Works() {
    assertEquals(5, tri1.getBoardSize());
    assertBoard(tri1, 0, 0);
  }

  @Test
  public void testConstructor2Works() {
    assertEquals(1, tri2.getBoardSize());
    assertBoard(tri2, 0, 0);
  }

  @Test
  public void testConstructor3Works() {
    assertEquals(5, tri3.getBoardSize());
    assertBoard(tri3, 3, 2);
  }

  @Test
  public void testConstructor4Works() {
    assertEquals(6, tri4.getBoardSize());
    assertBoard(tri4, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeSize() {
    tri2 = new TriangleSolitaireModel(-4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOOBCoordinates() {
    tri4 = new TriangleSolitaireModel(3, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCoordinates() {
    tri3 = new TriangleSolitaireModel(1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCoordinates2() {
    tri3 = new TriangleSolitaireModel(6, 4, 5);
  }

  @Test
  public void testMove() {
    assertMove(tri1, 2, 0, 0, 0);
    assertMove(tri1, 2, 2, 2, 0);
    assertMove(tri1, 0, 0, 2, 2);
    assertMove(tri1, 3, 3, 1, 1);
    assertMove(tri1, 3, 1, 3, 3);
    tri1 = new TriangleSolitaireModel();
    assertMove(tri1, 2, 0, 0, 0);
    assertMove(tri1, 3, 2, 1, 0);
    assertMove(tri1, 4, 0, 2, 0);
    assertMove(tri1, 1, 0, 3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMove1RowAway1()  {
    tri1.move(1, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMove1RowAway2()  {
    tri1.move(1, 1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameRowNotTwoSpaces1()  {
    tri1.move(2, 0, 0, 0);
    tri1.move(2, 1, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveSameRowNotTwoSpaces2()  {
    tri1.move(2, 0, 0, 0);
    tri1.move(4, 0, 2, 0);
    tri1.move(4, 3, 4, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveTwoRowsNotDiagonal()  {
    tri1.move(2, 1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveNoMarbleBetween()  {
    tri1.move(2, 0, 0, 0);
    tri1.move(0, 0, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromPositionNoMarble()  {
    tri1.move(0, 0, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveToPositionNotEmpty()  {
    tri1.move(3, 0, 3, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidDims()  {
    tri1.move(0, 1, 2, 1);
  }

  void assertBoard(MarbleSolitaireModel model, int row, int col) {
    for (int i = 0; i < model.getBoardSize(); i++) {
      for (int j = 0; j <= i; j++) {
        if (i == row && j == col) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(i, j));
        } else {
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(i, j));
        }
      }
    }
  }

}
