package hangman;

import java.util.List;
import java.util.Set;

public class HangManGame {

  private final HangManWordDifficulty wordDifficulty;
  private final List<String> wordList;
  private int currentWordIndex;
  private int wordWrongGuessesLeft;
  private int correctWordsCount, incorrectWordsCount;
  private Set<Character> wordGuessedLetters;

  public HangManGame(HangManWordDifficulty wordDifficulty, List<String> wordList) {
    this.wordDifficulty = wordDifficulty;
    this.wordList = wordList;
    resetWordWrongGuessesLeft();
  }
  
  public void resetWordWrongGuessesLeft() {
    setWordWrongGuessesLeft(6);
  }

  public int getWordWrongGuessesLeft() {
    return wordWrongGuessesLeft;
  }

  public void setWordWrongGuessesLeft(int wordWrongGuessesLeft) {
    this.wordWrongGuessesLeft = wordWrongGuessesLeft;
  }

  public int getCurrentWordIndex() {
    return currentWordIndex;
  }

  public void setCurrentWordIndex(int currentWordIndex) {
    this.currentWordIndex = currentWordIndex;
  }

  public List<String> getWordList() {
    return wordList;
  }

  public HangManWordDifficulty getWordDifficulty() {
    return wordDifficulty;
  }

  public int getCorrectWordsCount() {
    return correctWordsCount;
  }

  public void setCorrectWordsCount(int correctWordsCount) {
    this.correctWordsCount = correctWordsCount;
  }

  public int getIncorrectWordsCount() {
    return incorrectWordsCount;
  }

  public void setIncorrectWordsCount(int incorrectWordsCount) {
    this.incorrectWordsCount = incorrectWordsCount;
  }

  public Set<Character> getWordGuessedLetters() {
    return wordGuessedLetters;
  }

  public void setWordGuessedLetters(Set<Character> wordGuessedLetters) {
    this.wordGuessedLetters = wordGuessedLetters;
  }

}
