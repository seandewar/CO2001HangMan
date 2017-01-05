package hangman.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainMenuController implements Initializable {

  @FXML
  private Label menuOptionDesc;
  @FXML
  private Button menuOptionNewGame;
  @FXML
  private Button menuOptionLoadGame;
  
  @Override
  public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    updateOptionDescWithTarget(null);
  }
  
  private void updateOptionDescWithTarget(Node newTargetOption) {
    if (newTargetOption == menuOptionNewGame) {
      menuOptionDesc.setText("Start a new game of hangman at your preferred difficulty level.");
    } else if (newTargetOption == menuOptionLoadGame) {
      menuOptionDesc.setText("Continue playing your last saved game of hangman.");
    } else {
      menuOptionDesc.setText("Choose an option:");
    } 
  }

  @FXML
  private void handleMenuOptionNewGameAction(ActionEvent event) {
  }

  @FXML
  private void handleMenuOptionLoadGameAction(ActionEvent event) {
  }

  @FXML
  private void handleMenuOptionMouseEntered(MouseEvent event) {
    updateOptionDescWithTarget((Node)event.getSource());
  }

  @FXML
  private void handleMenuOptionMouseExited(MouseEvent event) {
    updateOptionDescWithTarget(null);
  }

}
