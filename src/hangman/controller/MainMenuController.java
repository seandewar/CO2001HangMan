package hangman.controller;

import java.net.URL;
import java.util.ResourceBundle;

import hangman.HangMan;
import hangman.HangManScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class MainMenuController implements Initializable {

  @FXML
  private Button menuOptionNewGame;
  @FXML
  private Button menuOptionLoadGame;
  
  @Override
  public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    menuOptionNewGame.setTooltip(new Tooltip(
        "Start a new game of hangman at your preferred difficulty level."));
    menuOptionLoadGame.setTooltip(new Tooltip("Continue playing your last saved game of hangman."));
  }
  
  @FXML
  private void handleMenuOptionNewGameAction(ActionEvent event) {
    HangMan.getInstance().changeScreen(HangManScreen.NewGame);
  }

  @FXML
  private void handleMenuOptionLoadGameAction(ActionEvent event) {
    // TODO
  }

}
