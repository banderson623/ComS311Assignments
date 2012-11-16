import java.util.Iterator;
import java.util.Set;

/**
 * User: brian_anderson
 * Date: 11/16/12
 * <p/>
 * Add some readme here about how this operates
 */
public class MainTest {
    public static String wordListFile = "Project7-Anagram/test/englishWords.txt";

    public static void main(String [ ] args){
        AnagramEnumerator ana = new AnagramEnumerator();

        ana.initialize(ana.readWordsIntoSetFromFileAt(wordListFile));

        String toTest = "brian"; System.out.println(toTest + " --> " + ana.enumerateAnagramsUnderE(toTest));

//        String toTest = "brian";
//        System.out.println(toTest + " --> " + ana.enumerateAnagramsUnderBagE(toTest));


    }
}
