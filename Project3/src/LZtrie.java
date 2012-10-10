/**
 * User: Brian Anderson, COM S 311 - Fall 2012
 * Date: 10/10/12
 * Time: 12:50 AM
 * You are to implement the following interface for compressing data using the
 * LZ algorithm using a trie as described above, and a second time using the Java HashMap data structure.
 *
 * Sources Referenced while implementing this algorithm:
 *  - http://en.wikipedia.org/wiki/Trie
 *  - https://forums.oracle.com/forums/thread.jspa?messageID=8787521
 *
 */
public class LZtrie implements ILZ {

    @Override
    public String encode(String uncompressed) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Todo: Remove this before submission, just for testing
     */
    public Trie trieFactory(){
        return new Trie();
    }

    public class Trie {
        private TrieNode rootNode;

        /** Just to get things started */
        public Trie(){
            Log l = new Log("Trie::Trie");
            l.log("Instantiated");
            rootNode = new TrieNode();
        }

        public boolean addString(String onlyOfOnesAndZeros){
            Log l = new Log("Trie::addString");
            l.log("Adding String \"" + onlyOfOnesAndZeros + "\"");
            return rootNode.addString(onlyOfOnesAndZeros);
        }

        public class TrieNode {
            //private TrieNode parent;
            private TrieNode left;
            private TrieNode right;

            public TrieNode(){
                Log l = new Log("TrieNode::TrieNode");
                l.log("Instantiated");
                //parent =    null;
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
                Log l = new Log("TrieNode::addString");
                l.log(onlyOfOnesAndZeros);
                if(onlyOfOnesAndZeros.length() > 0){
                    char value = onlyOfOnesAndZeros.charAt(0);
                    l.log("Adding/Checking '" + value + "'");
                    if(value == '0'){
                        // goes to left child
                        l.log("left");
                        if(left == null) left = new TrieNode();
                        return left.addString(onlyOfOnesAndZeros.substring(1));
                    } else if(value == '1') {
                        // goes to right child
                        l.log("right");
                        if(right == null) right = new TrieNode();
                        return right.addString(onlyOfOnesAndZeros.substring(1));
                    } else {
                        throw new InternalError("Only strings containing 0 and 1 are accepted " + onlyOfOnesAndZeros);
                    }
                } else {
                    l.log("empty string");
                    return false;
                }
            }
        }



    }


}
