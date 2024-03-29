package hangman;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DictionaryLoader {

  public static List<String> loadWords(Path dictionaryPath, int numWords) throws IOException {
    // Random deletion from an ArrayList isn't that slow...
    List<String> words = new ArrayList<String>(Files.readAllLines(dictionaryPath,
        StandardCharsets.UTF_8));
    List<String> chosenWords = new ArrayList<String>();

    // Choose numWords random words from this list
    // while making sure we don't choose more words than the dictionary has
    Random rand = new Random();
    for (int i = 0; i < numWords; ++i) {
      if (words.size() <= 0) {
        break;
      }

      chosenWords.add(words.remove(rand.nextInt(words.size())));
    }

    return chosenWords;
  }

}
