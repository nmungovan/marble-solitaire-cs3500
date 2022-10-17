package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.controller.ControllerFeatures;

/**
 * Panel interface.
 */
public interface Panel {

  /**
   * Method to take in feature.
   *
   * @param feature feature passed in
   */
  void panelInput(ControllerFeatures feature);

}
