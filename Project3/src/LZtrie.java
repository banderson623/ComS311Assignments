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
public class LZtrie extends LZEncodeGeneric<LZtrie.Trie> implements ILZ {

    @Override
    /**
     * Uncompressed string must contain only 1 and 0's
     * @returns LZ encoded string
     */
    public String encode(String uncompressed){
        return encodeWithStorage(uncompressed, new Trie());
    }

    /**
     * This classes implements a Trie binary tree structure to support
     * the interface LZDictionaryStoreInterface, which allows three
     * items:
     *
     *  boolean addString()
     *  boolean getMaxIndex()
     */
    public class Trie implements ILZ.LZDictionaryStoreInterface
    {
        /**
         * Top level trie node, initialized when the class is instantiated.
         */
        private TrieNode rootNode;

        /** Holds the size of the trie */
        private int atIndex;

        /** Just to get things started */
        public Trie()
        {
            atIndex = 1;
            rootNode = new TrieNode();
            rootNode.index = atIndex;
        }

        /**
         * Returns the maximum index of the Trie binary tree thus far
         * @return integer denoting the max index of the Trie tree
         */
        public int getMaxIndex()
        {
            return atIndex;
        }

        /**
         * Adds a string to the Trie tree
         * @param ofOnlyOnesAndZeros is a string of only ones and zeros
         * @return if the item was added or not
         */
        public boolean addString(String ofOnlyOnesAndZeros)
        {
            int newIndex = rootNode.addString(ofOnlyOnesAndZeros,atIndex);
            boolean wasAdded = false;
            if(newIndex != atIndex)
            {
                atIndex = newIndex;
                wasAdded = true;
            }
            return wasAdded;
        }

        public int indexOfString(String ofOnlyOnesAndZeros)
        {
            if(ofOnlyOnesAndZeros.length() == 0){
                return 0;
            } else {
                return rootNode.indexOfString(ofOnlyOnesAndZeros);
            }
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

            /* the index in the dictionary */
            private int index;

            /**
             * Simple constructor, sets up the node in a
             * default/empty state
             */
            public TrieNode()
            {
                left =      null;
                right =     null;
                index =     0;
            }

            /**
             * Adds a string to this node, which will recursively call down
             * and add nodes as needed
             *
             * @param onlyOfOnesAndZeros is a string of something like "0101001"
             * @return
             */
            private int addString(String onlyOfOnesAndZeros, int withIndex)
            {
                if(onlyOfOnesAndZeros.length() > 0)
                {
                    char firstCharacter = onlyOfOnesAndZeros.charAt(0);
                    if(firstCharacter == '0')
                    {
                        // goes to left child....
                        // at this point I need to check if there is only one
                        // thing remaining to add, if there is and left is NOT null
                        // we are not adding.
                        if(left != null && onlyOfOnesAndZeros.length() == 1){
                            //addWasNecessary = false;
                        } else {
                            if(left == null)
                            {
                                left = new TrieNode();
                                left.index = withIndex++;
                            }
                            withIndex = left.addString(onlyOfOnesAndZeros.substring(1), withIndex);
                        }
                    }
                    else if(firstCharacter == '1')
                    {
                        // goes to right child.....................
                        // at this point I need to check if there is only one
                        // thing remaining to add, if there is and right is NOT null
                        // we are not adding.
                        if(right != null && onlyOfOnesAndZeros.length() == 1){
                            //addWasNecessary = false;
                        } else {
                            if(right == null)
                            {
                                right = new TrieNode();
                                right.index = withIndex++;
                            }
                            withIndex = right.addString(onlyOfOnesAndZeros.substring(1),withIndex);
                        }
                    }
                    else
                    {
                        throw new IllegalArgumentException("Only strings containing 0 and 1 are accepted " + onlyOfOnesAndZeros);
                    }
                }
                return withIndex;

            }

            /**
             * Will check if the string is contained below in the Trie-tree
             * @param ofOnlyOnesAndZeros A string of 1010
             * @return true if the string is stored in the trie
             */
            private boolean doesContainString(String ofOnlyOnesAndZeros)
            {
                boolean wasFound = false;
                if(ofOnlyOnesAndZeros.length() == 0)
                {
                    // Found, we are in a real node and the string is empty.
                    wasFound = true;
                }
                else
                {
                    // Get the first character from the string
                    char firstCharacter = ofOnlyOnesAndZeros.charAt(0);
                    if(firstCharacter == '0')
                    {
                        // goes to left child
                        if(left != null)
                        {
                            wasFound = left.doesContainString(ofOnlyOnesAndZeros.substring(1));
                        }
                    }
                    else if(firstCharacter == '1')
                    {
                        // goes to right child
                        if(right != null)
                        {
                            wasFound = right.doesContainString(ofOnlyOnesAndZeros.substring(1));
                        }
                    }
                    else
                    {
                        //throw new InternalError("Only strings containing 0 and 1 are accepted " + onlyOfOnesAndZeros);
                        wasFound = false;
                    }
                }
                return wasFound;
            }

            private int indexOfString(String ofOnlyOnesAndZeros)
            {
                int indexOfFound = -1;
                if(ofOnlyOnesAndZeros.length() == 0)
                {
                    // Found, we are in a real node and the string is empty.
                    indexOfFound = index;
                }
                else
                {
                    // Get the first character from the string
                    char firstCharacter = ofOnlyOnesAndZeros.charAt(0);
                    if(firstCharacter == '0')
                    {
                        // goes to left child
                        if(left != null)
                        {
                            indexOfFound = left.indexOfString(ofOnlyOnesAndZeros.substring(1));
                        } else {
                            indexOfFound = -1;
                        }
                    }
                    else if(firstCharacter == '1')
                    {
                        // goes to right child
                        if(right != null)
                        {
                            indexOfFound = right.indexOfString(ofOnlyOnesAndZeros.substring(1));
                        } else {
                            indexOfFound = -1;
                        }
                    }
                    else
                    {
                        indexOfFound = -1;
                    }
                }
                return indexOfFound;
            }

        } // TrieNode
    }




}
