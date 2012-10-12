/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 10/10/12
 * Time: 12:41 AM
 * LZ algorithm using a trie as described above, and a second time using the Java HashMap data structure.
 */
public interface ILZ {
    /**
     * Assume that each character in the String parameters is either 0 or 1,
     * and encode must handle any binary string, including   (i.e., "").
     * @param uncompressed - A string of composed of 1 or 0
     * @return
     */
    public String encode(String uncompressed);

    public interface LZStore {
        public int getMaxIndexAt();
        public boolean addString(String ofOnlyOnesAndZeros);
        public int indexOfString(String ofOnlyOnesAndZeros);
    }
}
