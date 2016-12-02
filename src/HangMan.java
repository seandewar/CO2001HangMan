import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HangMan extends Application {

  private Label statusLabel;

  public void setStatus(String message) {
    System.out.println("Status: " + message);

    statusLabel.setText(message);
    statusLabel.setVisible(true);
  }

  public void resetStatus() {
    statusLabel.setVisible(false);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void start(Stage stage) {
    // Create and initialise status bar
    statusLabel = new Label();

    HBox statusBox = new HBox();
    statusBox.getChildren().addAll(statusLabel);

    // Box to display loaded words.
    TextArea loadedWordsBox = new TextArea();
    loadedWordsBox.setEditable(false);

    // Initialise root panel
    BorderPane root = new BorderPane();
    root.setCenter(loadedWordsBox);
    root.setBottom(statusBox);

    // Initialise stage
    stage.setTitle("HangMan!");
    stage.setScene(new Scene(root, 640, 480));
    stage.show();

    setStatus("Waiting for user...");

    // Load in parallel or not?
    Alert parallelLoadAlert = new Alert(Alert.AlertType.NONE,
        "Load the dictionary files in parallel using threads?", ButtonType.YES, ButtonType.NO);
    parallelLoadAlert.setTitle("Load using threads?");
    parallelLoadAlert.initOwner(stage);

    Optional<ButtonType> result = parallelLoadAlert.showAndWait();
    List<List<String>> allChosenWordLists = new ArrayList<List<String>>();

    // TEST: Choose 4 sets of 50 random words from each dictionary
    // We'll also be measuring how much time this process takes
    long startFetchTime = 0;
    try {
      if (result.get() == ButtonType.YES) {
        // Load using threads
        setStatus("Loading dictionaries in parallel...");
        loadedWordsBox.appendText(String.format("Parallel load!%n"));

        // Create thread pool and assign workers to our threads
        startFetchTime = System.currentTimeMillis();

        ExecutorService es = Executors.newCachedThreadPool();
        Future<List<String>>[] fetchFutures = new Future[4];
        for (int i = 0; i < fetchFutures.length; ++i) {
          fetchFutures[i] = es.submit(new DictionaryFetcherWorker(Paths.get("resources/file" + (i
              + 1) + ".txt"), 50));
        }

        // Finish using our thread pool and wait for future results
        es.shutdown();
        for (Future<List<String>> f : fetchFutures) {
          allChosenWordLists.add(f.get());
        }
      } else {
        // Load without threads
        setStatus("Loading dictionaries sequentially...");
        loadedWordsBox.appendText(String.format("Sequential load!%n"));

        startFetchTime = System.currentTimeMillis();

        for (int i = 0; i < 4; ++i) {
          allChosenWordLists.add(DictionaryLoader.fetchWords(Paths.get("resources/file" + (i + 1)
              + ".txt"), 50));
        }
      }

    } catch (IOException | InterruptedException | ExecutionException e) {
      System.out.println("Failed to fetch words:");
      e.printStackTrace();

      Alert errorAlert = new Alert(Alert.AlertType.ERROR);
      errorAlert.setHeaderText("Failed to fetch words!");
      errorAlert.setContentText(e.toString());
      errorAlert.initOwner(stage);

      errorAlert.showAndWait();
      Platform.exit();
      return;
    }

    // Print how long the process took
    setStatus("Finished loading dictionary files! Took " + (System.currentTimeMillis()
        - startFetchTime) + " ms.");

    // Show chosen words
    System.out.println("Lists: " + allChosenWordLists.size());
    loadedWordsBox.appendText(String.format("Loaded words: %n"));

    for (List<String> l : allChosenWordLists) {
      System.out.println("   Size: " + l.size());
      loadedWordsBox.appendText(String.format("%n    Size: %d%n", l.size()));

      for (String s : l) {
        System.out.println("      " + s);
        loadedWordsBox.appendText(String.format("      %s%n", s));
      }
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

}
