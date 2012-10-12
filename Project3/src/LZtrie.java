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
    private Trie trie;

    @Override
    /**
     * Uncompressed string must contain only 1 and 0's
     * @returns encoded string
     */
    public String encode(String uncompressed)
    {
        trie = new Trie();
        String encoded = "";
        int leftIndex = 0;
        int rightMoved = 0;
        String encodingChunkSeparator = " ";

        trie.addString(uncompressed.substring(leftIndex,leftIndex+rightMoved));
        while(leftIndex < uncompressed.length() - 1){
            leftIndex = leftIndex + rightMoved;
            rightMoved = 1;
            // while it is unable to add the new substring, keep advancing the right index by one
            // until we get a new set of undiscovered substring
            boolean isLastChunk = false;

            while(!isLastChunk && !trie.addString(uncompressed.substring(leftIndex, leftIndex + rightMoved)))
            {
                rightMoved++;

                if((leftIndex + rightMoved) >= uncompressed.length() - 1){
                    isLastChunk = true;
                    rightMoved = uncompressed.length() - leftIndex;
                }
            }

            String addChunk = uncompressed.substring(leftIndex,(leftIndex + rightMoved));
            int indexOfPreviousString = -1;

            if(isLastChunk){
                trie.addString(addChunk);

                /**
                 * If the final phrase has occurred before (the most likely scenario for most input strings),
                 * then do not output its final bit as part of the codeword; simply output its index.
                 */
                indexOfPreviousString = trie.indexOfString(addChunk);
                if(indexOfPreviousString > -1){
                    // nothing to do here
                } else {
                    indexOfPreviousString = trie.indexOfString(addChunk.substring(0,addChunk.length()-1));
                }

                // Log base 2 of the max index size
                int leadingZeros = (31 - Integer.numberOfLeadingZeros(trie.getMaxIndexAt()))+1; // Log base 2 of the max index size
                // Left pad with zeros accordingly
                String correctlyPaddedBinaryString = String.format("%" + leadingZeros + "s",Integer.toBinaryString(indexOfPreviousString)).replace(' ', '0');
                // Add to encoding
                encoded += encodingChunkSeparator + correctlyPaddedBinaryString;// addChunk.substring(addChunk.length()-1);
                // Move left index appropriately to denote done state
                leftIndex = uncompressed.length()-1;
            }

            if(!isLastChunk){
                // Need to get the index of the chunk minus the last digit
                /**
                 * Informally, to determine the codeword for the (n+1)th phrase p,
                 * find the dictionary index (order of addition to the dictionary)
                 * of the one-bit-shorter phrase that p extends, represent that integer in
                 * binary using (log2 n rounded up), and add the final bit
                 * of p to the end of that. Then add the new phrase to the dictionary and repeat.
                 */
                indexOfPreviousString = trie.indexOfString(addChunk.substring(0,addChunk.length()-1));

                // Log base 2 of the max index size
                int leadingZeros = (31 - Integer.numberOfLeadingZeros(trie.getMaxIndexAt()));
                // Left pad with zeros accordingly
                String correctlyPaddedBinaryString = String.format("%" + leadingZeros + "s",Integer.toBinaryString(indexOfPreviousString)).replace(' ', '0');
                // Add all of this to the encoding
                encoded += encodingChunkSeparator + correctlyPaddedBinaryString + addChunk.substring(addChunk.length()-1);;
            }
        }
        return encoded;
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
        /**
         * Top level trie node, initialized when the class is instantiated.
         */
        private TrieNode rootNode;

        private int atIndex;

        /** Just to get things started */
        public Trie()
        {
            Log l = new Log("Trie::Trie");
            l.log("Instantiated");
            atIndex = 1;
            rootNode = new TrieNode();
            rootNode.index = atIndex;
        }

        /**
         * Returns the maximum index of the Trie binary tree thus far
         * @return integer denoting the max index of the Trie tree
         */
        public int getMaxIndexAt()
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
            Log l = new Log("Trie::addString");
            l.log("Adding String \"" + ofOnlyOnesAndZeros + "\"");
            int newIndex = rootNode.addString(ofOnlyOnesAndZeros,atIndex);
            boolean wasAdded = false;
            if(newIndex != atIndex)
            {
                l.log("Index now at: " + newIndex);
                atIndex = newIndex;
                wasAdded = true;
            }
            return wasAdded;
        }

        public boolean doesContainString(String ofOnlyOnesAndZeros)
        {
            Log l = new Log("Trie::containsString");
            return rootNode.doesContainString(ofOnlyOnesAndZeros);
        }

        public int indexOfString(String ofOnlyOnesAndZeros)
        {
            Log l = new Log("Trie::indexOfString");
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
                Log l = new Log("TrieNode::TrieNode");
                l.log("Instantiated");
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
            public int addString(String onlyOfOnesAndZeros, int withIndex)
            {
                Log l = new Log("TrieNode::addString");
                l.log("Starting at index: " + withIndex);
                l.changeIndent(Log.MORE);
//                int newIndex = -1;

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
                        // goes to right child
                        l.log("right");

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
                        throw new InternalError("Only strings containing 0 and 1 are accepted " + onlyOfOnesAndZeros);
                    }
                } else {
                    l.log("empty string");
                    //addWasNecessary = true;
                }
                l.log("Returning with withIndex: " + withIndex);
                l.changeIndent(Log.LESS);
                return withIndex;

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

            public int indexOfString(String ofOnlyOnesAndZeros)
            {
                int indexOfFound = -1;
                Log l = new Log("TrieNode::indexOfString");
                l.changeIndent(Log.MORE);
                l.log(ofOnlyOnesAndZeros);
                if(ofOnlyOnesAndZeros.length() == 0)
                {
                    // Found, we are in a real node and the string is empty.
                    indexOfFound = index;
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
                            indexOfFound = left.indexOfString(ofOnlyOnesAndZeros.substring(1));
                        } else {
                            indexOfFound = -1;
                        }
                    }
                    else if(firstCharacter == '1')
                    {
                        // goes to right child
                        l.log("right");
                        if(right != null)
                        {
                            indexOfFound = right.indexOfString(ofOnlyOnesAndZeros.substring(1));
                        } else {
                            indexOfFound = -1;
                        }
                    }
                    else
                    {
                        l.log("Not an error, just dumb");
                        indexOfFound = -1;
                    }
                }
                l.log("Returning with indexOfFound: " + indexOfFound);
                l.changeIndent(Log.LESS);
                return indexOfFound;
            }

        } // TrieNode
    }




}
