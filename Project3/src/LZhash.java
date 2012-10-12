import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: brian_anderson
 * Date: 10/10/12
 * Time: 12:52 AM
 *
 * You are to implement the following interface for compressing data using the LZ algorithm using a trie as described above, and a second time using the Java HashMap data structure.
 *
 * However, the LZ dictionary must be stored in a trie data structure of your own creation.
 * Turn in the file LZtrie.java and any other source files you create.
 * Place all such files in the default package.
 */
public class LZhash extends LZEncodeGeneric<LZhash.HashDictionary> implements ILZ {
    private LZDictionaryStoreInterface dictionaryStore;

    @Override
    /**
     * Uncompressed string must contain only 1 and 0's
     * @returns LZ encoded string
     */
    public String encode(String uncompressed){
        return encodeWithStorage(uncompressed, new HashDictionary());
    }


    /**
     * Implements a HashMap to store the LZDictionary
     */
    public class HashDictionary implements ILZ.LZDictionaryStoreInterface
    {
        /**
         * This is the primary storage of this
         * The Key, String, is the string
         * The Value is the index of that inserted string
         */
        private HashMap<String, Integer> hash;

        public HashDictionary() {
            hash = new HashMap<String, Integer>();
        }

        @Override
        public int getMaxIndex() {
            return hash.size();
        }

        @Override
        public boolean addString(String ofOnlyOnesAndZeros) {
            if (hash.containsKey(ofOnlyOnesAndZeros)){
                return false;
            } else {
                hash.put(ofOnlyOnesAndZeros,hash.size());
                return true;
            }
        }

        @Override
        public int indexOfString(String ofOnlyOnesAndZeros) {
            if (hash.containsKey(ofOnlyOnesAndZeros)){
                return hash.get(ofOnlyOnesAndZeros);
            } else {
                return -1;
            }
        }
    }
}
