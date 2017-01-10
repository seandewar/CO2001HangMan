package hangman.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hangman.HangMan;
import hangman.HangManScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainMenuController implements Initializable {

  @FXML
  private Button menuOptionNewGame;
  @FXML
  private Button menuOptionLoadGame;
  
  @Override
  public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
  }
  
  @FXML
  private void handleMenuOptionNewGameAction(ActionEvent event) throws IOException {
    HangMan.getInstance().changeScreen(HangManScreen.NEW_GAME);
  }

  @FXML
  private void handleMenuOptionLoadGameAction(ActionEvent event) {
    // TODO
  }

}
