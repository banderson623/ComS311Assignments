import java.util.ArrayList;
import java.util.List;


public class LZDecoder {
    public static String decode(String z)
    {
        //initialize the dictionary
        List<String> dictionary = new ArrayList<String>();
        dictionary.add("");

        //chunkify the input string
        ArrayList<String> chunkedInput = chunkInput(z);

        //Now build the output
        StringBuilder x = new StringBuilder();
        int chunkIndex = 1;
        for(String currentChunk : chunkedInput)
        {
            //find the index of the prefix in the dictionary
            int prefixIndexLength = chunkSize(chunkIndex) - 1;
            int prefixIndex = Integer.parseInt(currentChunk.substring(0, prefixIndexLength), 2);

            //add the prefix, together with the copied bit (if it exists), to the output
            String prefix = dictionary.get(prefixIndex);
            String phrase = prefix + currentChunk.substring(prefixIndexLength);
            x.append(phrase);

            //add the new phrase to the dictionary at the next available index
            dictionary.add(phrase);

            chunkIndex++;
        }

        return x.toString();
    }

    /**
     * chunk the input.  This is possible because we know exactly how long every chunk will
     * be - without even needing to look at what is in the chunks!
     * @param z
     * @return
     */
    private static ArrayList<String> chunkInput(String z)
    {
        ArrayList<String> chunkedInput = new ArrayList<String>();
        int chunkIndex = 1;
        while(!z.isEmpty())
        {
            int chunkSize = chunkSize(chunkIndex);

            //the last string can be 1 smaller than it would otherwise be (no copied bit)
            if(chunkSize > z.length())
            {
                //But it cannot be any smaller than that!
                if(chunkSize - 1 > z.length())
                {
                    throw new IllegalArgumentException("The encoded string is not of a valid length.");
                }
                chunkedInput.add(z.substring(0, chunkSize - 1));
                z = z.substring(chunkSize - 1);
            }
            else
            {
                chunkedInput.add(z.substring(0, chunkSize));
                z = z.substring(chunkSize);
            }

            chunkIndex++;
        }

        return chunkedInput;
    }

    //returns the size, in bits, of the next chunk of the input.
    //1 + ceiling(log_2(chunkIndex))
    public static int chunkSize(int chunkIndex)
    {
        if(chunkIndex == 1)
        {
            return 2;
        }
        return (int) (1 + Math.ceil(Math.log(chunkIndex) / Math.log(2)));
    }
}