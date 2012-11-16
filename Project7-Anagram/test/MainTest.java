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
//        Set<String>words = ana.readWordsIntoSetFromFileAt(wordListFile);
//        Iterator<String> it = words.iterator();
//        for(int i = 1; i < 5 && it.hasNext(); i++)
//        {
//            System.out.println(it.next());
//        }

        String toTest = "brian";
        System.out.println(toTest);

        //System.out.println(ana.getAllAnagramPossibilitiesFromString(toTest));

        System.out.println(ana.enumerateAnagramsUnderE(toTest));

    }
}
