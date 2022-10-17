package cs3500.marblesolitaire;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.SwingGuiView;

/**
 * Represents the entry point to the {@code MarbleSolitaire} game program. Arguments passed in
 * dictate the game created.
 */
public final class MarbleSolitaire {

  /**
   * The main method that creates and facilitates the game with the user.
   *
   * @param args the arguments passed in by the player to alter the game
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireGuiView view = new SwingGuiView(model);
    view.refresh();
  }

  /*
  public static void main(String[] args) {
    String type = args[0];
    MarbleSolitaireModel model = null;
    MarbleSolitaireView view;
    MarbleSolitaireController ctrl;
    String size = "";
    String rHole = "";
    String cHole = "";
    for (int i = 1; i < args.length; i++) {
      switch (args[i]) {
        case "-size":
          size = args[++i];
          break;
        case "-hole":
          rHole = args[++i];
          cHole = args[++i];
          break;
        default:
          throw new IllegalArgumentException();
      }
    }
    boolean standard = size.isEmpty() && (rHole.isEmpty() || cHole.isEmpty());
    boolean customSize = !size.isEmpty() && (rHole.isEmpty() || cHole.isEmpty());
    boolean customHole = size.isEmpty() && (!rHole.isEmpty() && !cHole.isEmpty());
    boolean customSizeAndHole = !size.isEmpty() && (!rHole.isEmpty() && !cHole.isEmpty());
    switch (type) {
      case "english":
        if (standard) {
          model = new EnglishSolitaireModel();
        } else if (customSize) {
          model = new EnglishSolitaireModel(Integer.parseInt(size));
        } else if (customHole) {
          model = new EnglishSolitaireModel(Integer.parseInt(rHole), Integer.parseInt(cHole));
        } else if (customSizeAndHole) {
          model = new EnglishSolitaireModel(Integer.parseInt(size), Integer.parseInt(rHole),
              Integer.parseInt(cHole));
        }
        view = new MarbleSolitaireTextView(model);
        break;
      case "european":
        if (standard) {
          model = new EuropeanSolitaireModel();
        } else if (customSize) {
          model = new EuropeanSolitaireModel(Integer.parseInt(size));
        } else if (customHole) {
          model = new EuropeanSolitaireModel(Integer.parseInt(rHole), Integer.parseInt(cHole));
        } else if (customSizeAndHole) {
          model = new EuropeanSolitaireModel(Integer.parseInt(size), Integer.parseInt(rHole),
              Integer.parseInt(cHole));
        }
        view = new MarbleSolitaireTextView(model);
        break;
      case "triangular":
        if (standard) {
          model = new TriangleSolitaireModel();
        } else if (customSize) {
          model = new TriangleSolitaireModel(Integer.parseInt(size));
        } else if (customHole) {
          model = new TriangleSolitaireModel(Integer.parseInt(rHole), Integer.parseInt(cHole));
        } else if (customSizeAndHole) {
          model = new TriangleSolitaireModel(Integer.parseInt(size), Integer.parseInt(rHole),
              Integer.parseInt(cHole));
        }
        view = new TriangleSolitaireTextView(model);
        break;
      default:
        throw new IllegalArgumentException("The type must be one of 'english', 'european', or " +
            "'triangular'.");
    }
    ctrl = new MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in));
    ctrl.playGame();
  }
   */
}