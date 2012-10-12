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
public class LZhash implements ILZ {
    private LZStore store;

    @Override
    /**
     * Uncompressed string must contain only 1 and 0's
     * @returns encoded string
     */
    public String encode(String uncompressed)
    {
        store = new Hasher();
        String encoded = "";
        int leftIndex = 0;
        int rightMoved = 0;
        String encodingChunkSeparator = "";

        store.addString(uncompressed.substring(leftIndex, leftIndex + rightMoved));
        while(leftIndex < uncompressed.length() - 1){
            leftIndex = leftIndex + rightMoved;
            rightMoved = 1;
            // while it is unable to add the new substring, keep advancing the right index by one
            // until we get a new set of undiscovered substring
            boolean isLastChunk = false;

            while(!isLastChunk && !store.addString(uncompressed.substring(leftIndex, leftIndex + rightMoved)))
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
                store.addString(addChunk);

                /**
                 * If the final phrase has occurred before (the most likely scenario for most input strings),
                 * then do not output its final bit as part of the codeword; simply output its index.
                 */
                indexOfPreviousString = store.indexOfString(addChunk);
                if(indexOfPreviousString > -1){
                    // nothing to do here
                } else {
                    indexOfPreviousString = store.indexOfString(addChunk.substring(0,addChunk.length()-1));
                }

                // Log base 2 of the max index size
                int leadingZeros = (31 - Integer.numberOfLeadingZeros(store.getMaxIndexAt()))+1; // Log base 2 of the max index size
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
                indexOfPreviousString = store.indexOfString(addChunk.substring(0,addChunk.length()-1));

                // Log base 2 of the max index size
                int leadingZeros = (31 - Integer.numberOfLeadingZeros(store.getMaxIndexAt()));
                // Left pad with zeros accordingly
                String correctlyPaddedBinaryString = String.format("%" + leadingZeros + "s",Integer.toBinaryString(indexOfPreviousString)).replace(' ', '0');
                // Add all of this to the encoding
                encoded += encodingChunkSeparator + correctlyPaddedBinaryString + addChunk.substring(addChunk.length()-1);;
            }
        }
        return encoded;
    }

    public class Hasher implements LZStore
    {

        @Override
        public int getMaxIndexAt() {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean addString(String ofOnlyOnesAndZeros) {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int indexOfString(String ofOnlyOnesAndZeros) {
            return 0;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
