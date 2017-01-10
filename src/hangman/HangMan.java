package hangman;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class HangMan extends Application {

  private static HangMan instance;
  
  private Stage stage;
  private Map<String, Scene> loadedScreenScenes;
  
  public static HangMan getInstance() {
    if (instance == null) {
      instance = new HangMan();
    }

    return instance;
  }
  
  public static void main(String[] args) {
    launch(args);
  }

  public HangMan() {
    loadedScreenScenes = new HashMap<String, Scene>();
    if (instance == null) {
      instance = this;
    }
  }
  
  public Stage getStage() {
    return stage;
  }

  private void handleScreenChange(String fxmlResourceName) throws IOException {
    Scene screenScene = loadedScreenScenes.get(fxmlResourceName);
    if (screenScene == null) {
      // This screen hasn't been loaded yet - load and cache it now
      screenScene = new Scene(FXMLLoader.load(getClass().getResource(fxmlResourceName)));
      loadedScreenScenes.put(fxmlResourceName, screenScene);
    }
    
    stage.setScene(screenScene);
  }

  public void changeScreen(HangManScreen screen) throws UnsupportedOperationException, IOException {
    String targetFxmlResourceName = "";

    switch (screen) {
      case MAIN_MENU:
        targetFxmlResourceName = "/res/ui/fxml/menu.fxml";
        break;
        
      case NEW_GAME:
        targetFxmlResourceName = "/res/ui/fxml/newgame.fxml";
        break;
        
      case GAME:
        targetFxmlResourceName = "/res/ui/fxml/game.fxml";
        break;
        
      default:
        throw new UnsupportedOperationException("Unsupported screen " + screen.toString());
    }
    
    handleScreenChange(targetFxmlResourceName);
  }
  
  @Override
  public void start(Stage stage) {
    try {
      this.stage = stage;

      stage.setTitle("Sean's Hangman");
      stage.setResizable(false);
      changeScreen(HangManScreen.MAIN_MENU);
      stage.sizeToScene();
      stage.show();
    } catch (Exception e) {
      // Exception not handled - if it has reached here it's fatal!
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Sean's Hangman - Fatal Error");
      alert.setContentText("A fatal error occurred within the application. "
          + "Please make sure none of the game's files are missing or corrupt and try running the "
          + "application again.");
      
      // Exception stack trace info for alert dialog
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      
      TextArea detailsTextArea = new TextArea(sw.toString());
      detailsTextArea.setWrapText(true);
      detailsTextArea.setEditable(true);
      alert.getDialogPane().setExpandableContent(detailsTextArea);

      alert.showAndWait();
      System.exit(1); // Exit with an error status
    }
  }

}
