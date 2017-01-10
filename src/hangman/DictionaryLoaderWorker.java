package hangman;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

public class DictionaryLoaderWorker implements Callable<List<String>> {

  private Path dictionaryPath;
  private int numWords;

  public DictionaryLoaderWorker(Path dictionaryPath, int numWords) {
    this.dictionaryPath = dictionaryPath;
    this.numWords = numWords;
  }

  @Override
  public List<String> call() throws IOException {
    return DictionaryLoader.loadWords(dictionaryPath, numWords);
  }

}
