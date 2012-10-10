/**
 * User: Brian Anderson, COM S 311 - Fall 2012
 * Date: 10/10/12
 * Time: 12:50 AM
 * You are to implement the following interface for compressing data using the
 * LZ algorithm using a trie as described above, and a second time using the Java HashMap data structure.
 *
 * Sources Referenced while implementing this:
 *  - https://forums.oracle.com/forums/thread.jspa?messageID=8787521
 *
 */
public class LZtrie implements ILZ {

    @Override
    public String encode(String uncompressed) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public class Trie {
        private TrieNode rootNode;

        /** Just to get things started */
        public Trie(){
            rootNode = new TrieNode();
        }

        public class TrieNode {

        }

    }


}
