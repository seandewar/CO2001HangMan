package hangman;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HangMan extends Application {

  private static HangMan instance;
  
  private Stage stage;
  private Map<String, Scene> loadedScreenScenes;
  
  public static HangMan getInstance() {
    return instance;
  }
  
  public static void main(String[] args) {
    launch(args);
  }

  public HangMan() {
    instance = this;
    loadedScreenScenes = new HashMap<String, Scene>();
  }
  
  public Stage getStage() {
    return stage;
  }

  private void handleScreenChange(String fxmlResourceName) throws IOException {
    Scene screenScene = loadedScreenScenes.get(fxmlResourceName);
    if (screenScene == null) {
      // This screen hasn't been loaded yet
      screenScene = new Scene(FXMLLoader.load(getClass().getResource(fxmlResourceName)));
      loadedScreenScenes.put(fxmlResourceName, screenScene);
    }
    
    stage.setScene(screenScene);
  }

  public void changeScreen(HangManScreen screen) throws UnsupportedOperationException {
    String targetFxmlResourceName = "";

    switch (screen) {
      case MainMenu:
        targetFxmlResourceName = "/res/ui/fxml/menu.fxml";
        break;
        
      case NewGame:
        targetFxmlResourceName = "/res/ui/fxml/newgame.fxml";
        break;
        
      default:
        throw new UnsupportedOperationException("Unsupported screen " + screen.toString());
    }
    
    try {
      handleScreenChange(targetFxmlResourceName);
    } catch (IOException e) {
      // TODO Error Message
    }
  }
  
  @Override
  public void start(Stage stage) throws IOException {
    this.stage = stage;

    stage.setTitle("Sean's Hangman");
    stage.setResizable(false);
    changeScreen(HangManScreen.MainMenu);
    stage.sizeToScene();
    stage.show();
  }

}
