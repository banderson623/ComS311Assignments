import java.util.Map;
import java.util.Set;

/**
 * User: brian_anderson
 * Date: 11/16/12
 * <p/>
 * Add some readme here about how this operates
 */
public interface IAnagramEnumerator
{
    //Initializes the IAnagramEnumerator with the dictionary.
    //This is where you should set up the data structures you are storing the dictionary in
    public void initialize(Set<String> dictionary);

    //Generates the set of anagrams of s that are consistent with the dictionary provided by initialize
    public Set<String> enumerateAnagramsUnderE(String s);

    /**
     * enumerateAnagramUnderBagE returns a set of multisets of strings.
     * A multiset (also called a "bag") is a set where elements can be repeated multiple times.
     * For example, enumerateAnagramUnderBagE("situation") should return a set containing multisets like
     *  a. {"as", "tuition"}
     *  b. {"iota", "units"}
     *  c. {"situation"}
     *  d. {"a", "onus", "it", "it"}
     *
     *  The first three of these mutisets are also ordinary sets, but the last one is not an ordinary set because
     *  the word "it" is repeated.
     *
     *  In Java, a good way to represent a multiset is with a Map<String, Integer>.
     *  The keys in this map represent the strings, and the values represent the number of times their
     *  corresponding keys appear. For example, for enumerateAnagramUnderBagE("situation"),
     *  the maps returned should include
     *      a. {"as" -> 1, "tuition" -> 1}
     *      b. {"iota" -> 1, "units" -> 1}
     *      c. {"situation" -> 1}
     *      d. {"a" -> 1, "onus" -> 1, "it" -> 2}
     */
    //Generates the set of anagrams of s that are consistent with bag(E).
    public Set<Map<String, Integer>> enumerateAnagramsUnderBagE(String s);
}
