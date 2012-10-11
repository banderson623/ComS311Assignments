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
    public Trie trieFactory()
    {
        Log l = new Log("Trie::trieFactory"); l.log("Factorizing");
        return new Trie();
    }

    public class Trie
    {
        private TrieNode rootNode;

        /** Just to get things started */
        public Trie()
        {
            Log l = new Log("Trie::Trie");
            l.log("Instantiated");
            rootNode = new TrieNode();
        }

        /**
         * Adds a string to the Trie tree
         * @param ofOnlyOnesAndZeros is a string of only ones and zeros
         * @return if the item was added or not
         */
        public boolean addString(String ofOnlyOnesAndZeros)
        {
            Log l = new Log("Trie::addString");
            l.log("Adding String \"" + ofOnlyOnesAndZeros + "\"");
            return rootNode.addString(ofOnlyOnesAndZeros);
        }

        public boolean doesContainString(String ofOnlyOnesAndZeros)
        {
            Log l = new Log("Trie::containsString");
            return rootNode.doesContainString(ofOnlyOnesAndZeros);
        }

        /**
         * An individual node in the Trie Tree
         * Contains nodes to the left and right
         *
         * Left nodes contain substrings starting with 0's,
         * Right nodes contain substrings starting with 1's,
         */
        public class TrieNode
        {
            /* The left subtree of the node (0's) */
            private TrieNode left;

            /* The right subtree of the node (1's) */
            private TrieNode right;

            /**
             * Simple constructor, sets up the node in a
             * default/empty state
             */
            public TrieNode()
            {
                Log l = new Log("TrieNode::TrieNode");
                l.log("Instantiated");
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
            public boolean addString(String onlyOfOnesAndZeros)
            {
                Log l = new Log("TrieNode::addString");
                l.changeIndent(Log.MORE);
                boolean addWasNecessary = false;

                l.log(onlyOfOnesAndZeros);
                if(onlyOfOnesAndZeros.length() > 0)
                {
                    char firstCharacter = onlyOfOnesAndZeros.charAt(0);
                    l.log("Adding/Checking '" + firstCharacter + "'");
                    if(firstCharacter == '0')
                    {
                        // goes to left child
                        l.log("left");

                        // at this point I need to check if there is only one
                        // thing remaining to add, if there is and left is NOT null
                        // we are not adding.
                        if(left != null && onlyOfOnesAndZeros.length() == 1){
                            addWasNecessary = false;
                        } else {
                            if(left == null){
                                left = new TrieNode();
                            }
                            addWasNecessary = left.addString(onlyOfOnesAndZeros.substring(1));
                        }
                    }
                    else if(firstCharacter == '1')
                    {
                        // goes to right child
                        l.log("right");

                        // at this point I need to check if there is only one
                        // thing remaining to add, if there is and right is NOT null
                        // we are not adding.
                        if(right != null && onlyOfOnesAndZeros.length() == 1){
                            addWasNecessary = false;
                        } else {
                            if(right == null) right = new TrieNode();
                            addWasNecessary = right.addString(onlyOfOnesAndZeros.substring(1));
                        }
                    }
                    else
                    {
                        throw new InternalError("Only strings containing 0 and 1 are accepted " + onlyOfOnesAndZeros);
                    }
                } else {
                    l.log("empty string");
                    addWasNecessary = true;
                }
                l.log("Returning with addWasNecessary: " + addWasNecessary);
                l.changeIndent(Log.LESS);
                return addWasNecessary;

            }

            /**
             * Will check if the string is contained below in the Trie-tree
             * @param ofOnlyOnesAndZeros A string of 1010
             * @return true if the string is stored in the trie
             */
            public boolean doesContainString(String ofOnlyOnesAndZeros)
            {
                boolean wasFound = false;
                Log l = new Log("TrieNode::doesContainString");
                l.changeIndent(Log.MORE);
                l.log(ofOnlyOnesAndZeros);
                if(ofOnlyOnesAndZeros.length() == 0)
                {
                    // Found, we are in a real node and the string is empty.
                    wasFound = true;
                }
                else
                {
                    // Get the first character from the string
                    char firstCharacter = ofOnlyOnesAndZeros.charAt(0);
                    l.log("Checking '" + firstCharacter + "'");
                    if(firstCharacter == '0')
                    {
                        // goes to left child
                        l.log("left");
                        if(left != null)
                        {
                            wasFound = left.doesContainString(ofOnlyOnesAndZeros.substring(1));
                        } else {
                            wasFound = false;
                        }
                    }
                    else if(firstCharacter == '1')
                    {
                        // goes to right child
                        l.log("right");
                        if(right != null)
                        {
                            wasFound = right.doesContainString(ofOnlyOnesAndZeros.substring(1));
                        } else {
                            wasFound = false;
                        }
                    }
                    else
                    {
                        //throw new InternalError("Only strings containing 0 and 1 are accepted " + onlyOfOnesAndZeros);
                        l.log("Not an error, just dumb");
                        wasFound = false;
                    }
                }
                l.log("Returning with Wasfound: " + wasFound);
                l.changeIndent(Log.LESS);
                return wasFound;
            }

        } // TrieNode
    }


}
