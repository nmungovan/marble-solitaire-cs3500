import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Represents a Readable object that is "corrupt" and does not correctly do its job of piping
 * input to the {@code MarbleSolitaireController}.
 */
public class CorruptReadable implements Readable {
  /**
   * Throws a new IOException when called ("corrupted").
   *
   * @param cb the buffer to read characters into
   * @return IOException
   * @throws IOException always!
   */
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("Bad input!");
  }
}
