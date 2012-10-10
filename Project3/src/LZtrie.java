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

        public boolean addString(String onlyOfOnesAndZeros){
            return rootNode.addString(onlyOfOnesAndZeros);
        }

        public class TrieNode {
            private TrieNode parent;
            private TrieNode left;
            private TrieNode right;

            public TrieNode(){
                parent =    null;
                left =      null;
                right =     null;
            }

            /**
             * Adds a string to this node, which will recursively call down
             * and add nodes as needed
             *
             * @param onlyOfOnesAndZeros is a string of something like "0101001"
             * @return
             */
            public boolean addString(String onlyOfOnesAndZeros){
                return true;
            }
        }



    }


}
