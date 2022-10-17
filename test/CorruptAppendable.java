import java.io.IOException;

/**
 * Represents an Appendable object that is "corrupt" and does not correctly do its job of accepting
 * output from the {@code MarbleSolitaireController}.
 */
public class CorruptAppendable implements Appendable {
  /**
   * Throws an IOException when called.
   *
   * @param csq
   *         The character sequence to append.  If {@code csq} is
   *         {@code null}, then the four characters {@code "null"} are
   *         appended to this Appendable.
   *
   * @return an IOException
   * @throws IOException when called.
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("BAD INPUT!! :(");
  }

  /**
   * Throws an IOException when called.
   *
   * @param csq
   *         The character sequence from which a subsequence will be
   *         appended.  If {@code csq} is {@code null}, then characters
   *         will be appended as if {@code csq} contained the four
   *         characters {@code "null"}.
   *
   * @param start
   *         The index of the first character in the subsequence
   *
   * @param end
   *         The index of the character following the last character in the
   *         subsequence
   *
   * @return an IOException
   * @throws IOException when called.
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("BAD INPUT!! :(");
  }

  /**
   * Throws an IOException when called.
   *
   * @param c
   *         The character to append
   *
   * @return an IOException
   * @throws IOException when called.
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("BAD INPUT!! :(");
  }
}
