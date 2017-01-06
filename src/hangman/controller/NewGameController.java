package hangman.controller;

import java.net.URL;
import java.util.ResourceBundle;

import hangman.HangMan;
import hangman.HangManScreen;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;

public class NewGameController implements Initializable {

  @FXML
  private ChoiceBox<String> newGameDifficulty;
  @FXML
  private Button newGameStart;
  @FXML
  private Button newGameMainMenu;
  
  @Override
  public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    // Populate difficulty choices and select the first
    newGameDifficulty.setItems(FXCollections.observableArrayList("Easy (5 letters)",
        "Medium (6 letters)", "Hard (7 letters)", "Expert (8 letters)"));
    newGameDifficulty.getSelectionModel().selectFirst();
    
    newGameDifficulty.setTooltip(new Tooltip(
        "Change the amount of letters that the words to solve have."));
    newGameStart.setTooltip(new Tooltip("Start a new game with the selected word difficulty."));
    newGameMainMenu.setTooltip(new Tooltip("Return to the main menu."));
  }

  @FXML
  private void handleNewGameStartAction(ActionEvent event) {
    // TODO
  }

  @FXML
  private void handleNewGameMainMenuAction(ActionEvent event) {
    HangMan.getInstance().changeScreen(HangManScreen.MainMenu);
  }

}
