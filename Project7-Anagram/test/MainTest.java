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
        Set<String>words = ana.readWordsIntoSetFromFileAt(wordListFile);
        Iterator<String> it = words.iterator();

        ana.initialize(ana.readWordsIntoSetFromFileAt(wordListFile));

        for(int i = 1; i < 5 && it.hasNext(); i++)
        {
            System.out.println(it.next());
        }

    }
}
