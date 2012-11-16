import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * You are to write a program that solves the anagram enumeration problem by
 * properly implementing the attached interface. Your solutions should utilize
 * pruning techniques (see an excellent discussion in the textbook on p. 242 for ideas)
 * to improve the running time of your solution.
 * Your submission should include:
 *
 *
 * Note I have used the following sites and articles as refernces some of
 * the techniques I've implimented in this class:
 *  - http://stackoverflow.com/questions/2705504/java-anagram-solver
 *  - http://stackoverflow.com/questions/55210/algorithm-to-generate-anagrams
 *  - http://www.wordsmith.org/anagram/index.html
 *  - http://www.cs.washington.edu/education/courses/cse143/08sp/handouts/23.html
 *
 */

public class AnagramEnumerator implements IAnagramEnumerator {
    //The underlying storage
    protected HashSet<String> dictionary;

    public AnagramEnumerator(){
        dictionary = new HashSet<String>();
    }

    /**
     * Handy way to read from the file into an array
     *
     * @param filePath the path to a file that has one word per line
     * @return a Set<String> where each entry corresponds to a word in the file
     */
    public Set<String> readWordsIntoSetFromFileAt(String filePath)
    {
        HashSet<String> tmpDictionary = new HashSet<String>();


        try {
            BufferedReader wordList = new BufferedReader(new FileReader(filePath));
            String word;
            while (null!= (word = wordList.readLine()))
            {
                tmpDictionary.add(word);
            }
            wordList.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return tmpDictionary;
    }

    /**
     * Saves the passed in dictionary as a hashset for internal use
     * @param dictionary
     */
    @Override
    public void initialize(Set<String> dictionary)
    {
        this.dictionary = (HashSet) dictionary;
    }

    @Override
    public Set<String> enumerateAnagramsUnderE(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<Map<String, Integer>> enumerateAnagramsUnderBagE(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }




    protected Set<String> getAllAnagramPossibilitiesFromString(String word)
    {
        HashSet<String> possible = new HashSet<String>();

        for(int i = 0; i < word.length(); i++){
            for(j = 0)
        }


        return possible;
    }

}
