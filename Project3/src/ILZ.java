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

    /**
     * LZDictionaryStoreInterface is an interface that supports the Lempel Ziv Compression
     * algorithm's storage and retrieval need for the dictionary
     */
    public interface LZDictionaryStoreInterface {

        /**
         * Gets the largest index value of the dictionary, should return the size
         * @return an integer denoting size
         */
        public int getMaxIndex();

        /**
         * Adds a new string to the dictionary, only saved/added if this is a unique
         * string. If it is not a unique string it returns false, otherwise true.
         *
         * It will throw an error if there are characters other than 0 or 1
         * @param ofOnlyOnesAndZeros A string of composed of '1' and '0'
         * @return boolean, true if it was added (because it is unique), false if it already existed
         */
        public boolean addString(String ofOnlyOnesAndZeros);

        /**
         * Returns the index of the string. The index is determined by the insertion order.
         * If the string is not found -1 is returned
         * @param ofOnlyOnesAndZeros The string to look up in the dictionary
         * @return returns the index of the string in the dictionary, -1 if not found.
         */
        public int indexOfString(String ofOnlyOnesAndZeros);
    }
}
