import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * You are to write a program that solves the anagram enumeration problem by
 * properly implementing the attached interface. Your solutions should utilize
 * pruning techniques (see an excellent discussion in the textbook on p. 242 for ideas)
 * to improve the running time of your solution.
 * Your submission should include:
 *
 *
 * Note I have used the following sites and articles as reference:
 *
 *  - http://stackoverflow.com/questions/2705504/java-anagram-solver
 *  - http://stackoverflow.com/questions/55210/algorithm-to-generate-anagrams
 *  - http://www.wordsmith.org/anagram/index.html
 *  - http://www.cs.washington.edu/education/courses/cse143/08sp/handouts/23.html
 *  - https://www.cs.washington.edu/education/courses/143/11wi/homework/6/spec.pdf
 *  - http://stackoverflow.com/questions/5072985/java-stackoverflowerror-bad-recursive-call
 *
 */

public class AnagramEnumerator implements IAnagramEnumerator {
    //The underlying storage
    protected HashSet<String> dictionary;

    public static String recursingTabs = "";

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
        Iterator<String> it = dictionary.iterator();
        while(it.hasNext()){
            this.dictionary.add(it.next());
        }
    }

    @Override
    public Set<String> enumerateAnagramsUnderE(String s) {
        HashSet<String> possible = (HashSet) getPossible(s,"");
        HashSet<String> goodOnes = new HashSet<String>();
        for(String word : possible)
        {
            // Only use the words of the correct size
            if(word.length() == s.length()){
                goodOnes.add(word);
            }
        }

        return goodOnes;
    }


    protected Set<String> getPossible(String possibleLetters,
                                      String usedSoFar)
    {
        // This is the collection of possible words
        // (those sets of characters that are in the dictionary)
        HashSet<String> possible = new HashSet<String>();

        // If this current word (collection characters) is in the dictionary
        // add it to the possible ones
        if(this.dictionary.contains(usedSoFar)){
            possible.add(usedSoFar);
        }

        // Now for each of the unused letters (those that are possible to use)
        // loop through them and recursively try them
        for(int i = 0; i < possibleLetters.length(); ++i)
        {
            //gets the current letter we are working on
            String letter = possibleLetters.substring(i,i+1);
            String allTheOtherLetters = possibleLetters.substring(0,i) + possibleLetters.substring(i+1);
            possible.addAll(getPossible(allTheOtherLetters, usedSoFar+letter));
        }

        return possible;
    }





    @Override
    public Set<Map<String, Integer>> enumerateAnagramsUnderBagE(String inputString) {
        Set<Map<String, Integer>> results = new HashSet<Map<String, Integer>>();
        results = getPossibleBags(inputString, "", new HashMap<String, Integer>());
        return results;
    }



    protected  Set<Map<String, Integer>> getPossibleBags(String possibleLetters,
                                                         String usedSoFar,
                                                         Map<String,Integer> currentBag)
    {
        // This is the collection of possible words
        // (those sets of characters that are in the dictionary)
        HashSet<String> possible = new HashSet<String>();
        Set<Map<String, Integer>> bags = new HashSet<Map<String, Integer>>();

        // If this current word (collection characters) is in the dictionary
        // add it to the possible ones
        if(this.dictionary.contains(usedSoFar)){
            //now add a this bag, and make a new word
            Map<String,Integer> bag = new HashMap<String,Integer>();
            bag.put(usedSoFar,1);
            bags.add(bag);

            // Now for each of the unused letters (those that are possible to use)
            // loop through them and recursively try them
            for(int i = 0; i < possibleLetters.length(); ++i)
            {
                //gets the current letter we are working on
                String letter = possibleLetters.substring(i,i+1);
                String allTheOtherLetters = possibleLetters.substring(0,i) + possibleLetters.substring(i+1);
                Set<String> words = getPossible(allTheOtherLetters,usedSoFar + letter);
                for(String word : words)
                {
//                    if(usedSoFar.length())
                }
//                bags.addAll(getPossibleBags(allTheOtherLetters, usedSoFar+letter));
            }



        }

//        // Now for each of the unused letters (those that are possible to use)
//        // loop through them and recursively try them
//        for(int i = 0; i < possibleLetters.length(); ++i)
//        {
//            //gets the current letter we are working on
//            String letter = possibleLetters.substring(i,i+1);
//            String allTheOtherLetters = possibleLetters.substring(0,i) + possibleLetters.substring(i+1);
//            bags.addAll(getPossibleBags(allTheOtherLetters, usedSoFar+letter));
//        }
//
        return bags;
    }

}
