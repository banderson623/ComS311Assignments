/**
 * This is a generic encoding class that implements the dirty work of the LZ
 * encoding.
 *
 * The trick for this assignment is that is uses Java Generics to
 * reference which type of dictionary storage will be used
 */
public class LZEncodeGeneric<T extends ILZ.LZDictionaryStoreInterface>
{
    private boolean debug = false;

    private void  showState(String input,int left, int right){
        if(debug){
            System.out.println("");
//            System.out.println(""+input + " [" + input.length()+ "]");
//            if(left < 1) left = 1;
//            if(right < 1) right = 1;
//            System.out.format("%"+ (left) + "s%"+ (right) + "s%n","^","^");
//            System.out.format("%"+ (left) + "s%"+ (right) + "s%n","l","r");
//            System.out.println("Left: " + left + " - Right: " + right);
        }
    }

    /**
     * Implements the LZ enconding using a Storage type that implements LZDictionaryStoreInterface
     * @param uncompressed The uncompressed string
     * @param dictionaryStore an instance of a class that implements LZDictionaryStoreInterface
     * @return the encoded/compressed string
     */
    protected String encodeWithStorage(String uncompressed, ILZ.LZDictionaryStoreInterface dictionaryStore) {
//        if(debug) System.out.println("encodeWithStorage >> '" +uncompressed + "'");

        String encoded = "";
        int leftIndex = 0;
        int rightMoved = 0;
        int integerAddressSize = 31;
        String encodingChunkSeparator = "";

        dictionaryStore.addString(uncompressed.substring(leftIndex, leftIndex + rightMoved));

        showState(uncompressed, leftIndex,rightMoved);
        if(uncompressed.length() == 1){
            //something special here...
            encoded = "0"+uncompressed;
        }

        while(leftIndex < uncompressed.length() - 1){
            leftIndex = leftIndex + rightMoved;
            rightMoved = 1;
            // while it is unable to add the new substring, keep advancing the right index by one
            // until we get a new set of undiscovered substring
            boolean isLastChunk = false;

            // Not the last chunk and string was not added, keep looping
            while((leftIndex + rightMoved) < uncompressed.length() &&
                  !dictionaryStore.addString(uncompressed.substring(leftIndex, leftIndex + rightMoved)))
            {
                showState(uncompressed, leftIndex,rightMoved);
                rightMoved++;
            }

            // Check for last chunk
            if((leftIndex + rightMoved) > uncompressed.length() - 1){
                isLastChunk = true;
                rightMoved = uncompressed.length() - leftIndex;
            }

            String addChunk = uncompressed.substring(leftIndex,(leftIndex + rightMoved));
            int indexOfPreviousString;


            if(isLastChunk){
                String paddedBinaryString="";
                //first check if we have this else where
                indexOfPreviousString = dictionaryStore.indexOfString(addChunk);
                if(indexOfPreviousString > -1){
                    // this is else where just add the index of this string with the correct chunk size
                    int numberOfDigits = chunkSize(dictionaryStore.getMaxIndex()) - 1;
                    paddedBinaryString = leftPadWith(Integer.toBinaryString(indexOfPreviousString), numberOfDigits, '0');
                } else {
                    //Now we will see if we can get this string but one shorter
                    if(addChunk.length() > 1){
                        indexOfPreviousString = dictionaryStore.indexOfString(addChunk.substring(0, addChunk.length() - 1));
                        if(indexOfPreviousString > -1){
                            // we found one less than the last
                            int numberOfDigits = chunkSize(dictionaryStore.getMaxIndex())-1;
                            paddedBinaryString = leftPadWith((Integer.toBinaryString(indexOfPreviousString) + addChunk.substring(addChunk.length() - 1)), numberOfDigits, '0');

                        }
                    }
                }

                if(paddedBinaryString.length() <= 0) {
                    int numberOfDigits = chunkSize(dictionaryStore.getMaxIndex());
                    paddedBinaryString = leftPadWith(addChunk, numberOfDigits, '0');

                }
                encoded += encodingChunkSeparator + paddedBinaryString;

                // Move left index appropriately to denote done state
                leftIndex = uncompressed.length()-1;
            }

            if(!isLastChunk){
                /**
                 * Informally, to determine the codeword for the (n+1)th phrase p,
                 * find the dictionary index (order of addition to the dictionary)
                 * of the one-bit-shorter phrase that p extends, represent that integer in
                 * binary using (log2 n rounded up), and add the final bit
                 * of p to the end of that. Then add the new phrase to the dictionary and repeat.
                 */
                indexOfPreviousString = dictionaryStore.indexOfString(addChunk.substring(0, addChunk.length() - 1));

                // Log base 2 of the max index size
                int max_index = dictionaryStore.getMaxIndex();
                int chunkSize = (integerAddressSize - Integer.numberOfLeadingZeros(max_index));
                if(max_index > 5){// && max_index < 8){
                    chunkSize++;
                }

//                if(max_index > 8){// && max_index < 8){
//                    chunkSize++;
//                }

//                int chunkSize = chunkSize(dictionaryStore.getMaxIndex());
//                if(chunkSize < 1) chunkSize = 1;
//                System.out.println("Leading zeros**: " + chunkSize + " from maxIndex: " + max_index);
//                System.out.println("Leading zeros**: " + (chunkSize(max_index-1)) + " from maxIndex: " + max_index);


                // Left pad with zeros accordingly
                String correctlyPaddedBinaryString = leftPadWith(Integer.toBinaryString(indexOfPreviousString),chunkSize,'0');
//                String correctlyPaddedBinaryString = String.format("%" + chunkSize + "s",Integer.toBinaryString(indexOfPreviousString)).replace(' ', '0');

                String encodedNugget = encodingChunkSeparator + correctlyPaddedBinaryString + addChunk.substring(addChunk.length()-1);

//                System.out.println(" <<< Adding encoding chunk: " + encodedNugget);

                // Add all of this to the encoding
                encoded += encodedNugget;
            }
        }
        return encoded;
    }


    private static int chunkSize(int chunkIndex)
    {
        if(chunkIndex == 1)
        {
            return 2;
        }
        return (int) (1 + Math.ceil(Math.log(chunkIndex) / Math.log(2)));
    }

    private static String leftPadWith(String value, int desiredLength, char fill)
    {
        StringBuilder str = new StringBuilder();
        int zerosNeeded = desiredLength - value.length();

        for(int i = 0; i < zerosNeeded; ++i) str.append(fill);

        str.append(value);
//        System.out.println("Building " + value + " to be " + str.toString());
        return str.toString();
    }
}
