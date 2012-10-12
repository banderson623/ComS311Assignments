/**
 * This is a generic encoding class that implements the dirty work of the LZ
 * encoding.
 *
 * The trick for this assignment is that is uses Java Generics to
 * reference which type of dictionary storage will be used
 */
public class LZEncodeGeneric<T extends ILZ.LZDictionaryStoreInterface>
{
    /**
     * Implements the LZ enconding using a Storage type that implements LZDictionaryStoreInterface
     * @param uncompressed The uncompressed string
     * @param dictionaryStore an instance of a class that implements LZDictionaryStoreInterface
     * @return the encoded/compressed string
     */
    protected String encodeWithStorage(String uncompressed, ILZ.LZDictionaryStoreInterface dictionaryStore) {

        String encoded = "";
        int leftIndex = 0;
        int rightMoved = 0;
        int integerAddressSize = 31;
        String encodingChunkSeparator = "";

        dictionaryStore.addString(uncompressed.substring(leftIndex, leftIndex + rightMoved));
        while(leftIndex < uncompressed.length() - 1){
            leftIndex = leftIndex + rightMoved;
            rightMoved = 1;
            // while it is unable to add the new substring, keep advancing the right index by one
            // until we get a new set of undiscovered substring
            boolean isLastChunk = false;

            while(!isLastChunk && !dictionaryStore.addString(uncompressed.substring(leftIndex, leftIndex + rightMoved)))
            {
                rightMoved++;

                if((leftIndex + rightMoved) >= uncompressed.length() - 1){
                    isLastChunk = true;
                    rightMoved = uncompressed.length() - leftIndex;
                }
            }

            String addChunk = uncompressed.substring(leftIndex,(leftIndex + rightMoved));
            int indexOfPreviousString;

            if(isLastChunk){
                dictionaryStore.addString(addChunk);

                /**
                 * If the final phrase has occurred before (the most likely scenario for most input strings),
                 * then do not output its final bit as part of the codeword; simply output its index.
                 */
                indexOfPreviousString = dictionaryStore.indexOfString(addChunk);
                if(indexOfPreviousString > -1){
                    // nothing to do here
                } else {
                    indexOfPreviousString = dictionaryStore.indexOfString(addChunk.substring(0, addChunk.length() - 1));
                }

                // Log base 2 of the max index size
                int leadingZeros = (integerAddressSize - Integer.numberOfLeadingZeros(dictionaryStore.getMaxIndex()))+1; // Log base 2 of the max index size
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
                indexOfPreviousString = dictionaryStore.indexOfString(addChunk.substring(0, addChunk.length() - 1));

                // Log base 2 of the max index size
                int leadingZeros = (integerAddressSize - Integer.numberOfLeadingZeros(dictionaryStore.getMaxIndex()));
                // Left pad with zeros accordingly
                String correctlyPaddedBinaryString = String.format("%" + leadingZeros + "s",Integer.toBinaryString(indexOfPreviousString)).replace(' ', '0');
                // Add all of this to the encoding
                encoded += encodingChunkSeparator + correctlyPaddedBinaryString + addChunk.substring(addChunk.length()-1);
            }
        }
        return encoded;
    }
}
