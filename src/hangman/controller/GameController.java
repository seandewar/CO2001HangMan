package hangman.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class GameController implements Initializable {

  @FXML
  private ImageView gallows;
  @FXML
  private Button saveAndMainMenu;
  @FXML
  private Label wordDifficulty;
  @FXML
  private Label wordGuessStatus;
  @FXML
  private Label wordGuessedLetters;
  @FXML
  private Label wordWrongGuessesLeft;
  @FXML
  private Label wordsLeft;
  @FXML
  private Label correctWords;
  @FXML
  private Label incorrectWords;
  
  @Override
  public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
  }
  
  

}
