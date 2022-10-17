package cs3500.marblesolitaire.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import cs3500.marblesolitaire.controller.ControllerFeatures;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents a GUI view that is implemented using Java Swing.
 */
public class SwingGuiView extends JFrame implements MarbleSolitaireGuiView {

  //the custom panel on which the board will be drawn
  private BoardPanel boardPanel;
  //the model state
  private MarbleSolitaireModelState modelState;
  //a label to display the score
  private JLabel scoreLabel;
  //a label to display any messages to the user
  private JLabel messageLabel;

  /**
   * Creates SwingGuiView.
   *
   * @param state model state passed in
   */
  public SwingGuiView(MarbleSolitaireModelState state) {
    super("Marble solitaire");
    this.modelState = state;
    /* main frame uses a border layout. Read about it here:
     *
     * */

    this.setLayout(new BorderLayout());
    //initialize the custom board with the model state
    boardPanel = new BoardPanel(this.modelState);
    //add custom board to the center of the frame
    this.add(boardPanel, BorderLayout.CENTER);
    //create the score label
    this.scoreLabel = new JLabel();
    //create the message label
    this.messageLabel = new JLabel();
    //put them both in a panel. This is done mostly to arrange them properly
    JPanel panel = new JPanel();
    /*
    the panel uses a grid layout with two columns. The gridlayout
    will stretch the labels so that they are exactly half of the width
    of this panel.

    Since we mention that we want a grid of 2 columns, and we
    add exactly two things to it, it will use one row.
     */

    panel.setLayout(new GridLayout(0, 2));
    panel.add(scoreLabel);
    panel.add(messageLabel);
    //add this panel to the bottom of the frame
    this.add(panel, BorderLayout.PAGE_END);
    pack();
    setVisible(true);
  }

  /**
   * Refresh the screen. This is called when the something on the
   * screen is updated and therefore it must be redrawn.
   */
  public void refresh() {
    //refresh the score
    this.scoreLabel.setText("Score: " + modelState.getScore());
    //this repaints the frame, which cascades to everything added
    //in the frame
    this.repaint();
  }

  @Override
  public void renderMessage(String message) {
    this.messageLabel.setText(message);
  }

  @Override
  public void viewInput(ControllerFeatures feature) {
    this.boardPanel.panelInput(feature);
  }
}

