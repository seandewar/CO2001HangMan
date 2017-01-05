package hangman;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HangMan extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/res/ui/fxml/menu.fxml"));
    Scene mainMenuScene = new Scene(root);

    stage.setTitle("Sean's Hangman");
    stage.setResizable(false);
    stage.setScene(mainMenuScene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}
